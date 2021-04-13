package fr.carbonit.checker;

import fr.carbonit.checker.adventurer.AdventurerChecker;
import fr.carbonit.checker.board.BoardChecker;
import fr.carbonit.checker.exception.CheckerException;
import fr.carbonit.checker.exception.ViolationsDetectedException;
import fr.carbonit.checker.mountain.MountainChecker;
import fr.carbonit.checker.treasure.TreasureChecker;
import fr.carbonit.model.objects.*;
import lombok.NonNull;
import utils.ListCastUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CentralChecker {
  @NonNull private final List<GameObject> parsedParameters;
  @NonNull private final Board reference;
  @NonNull private List<Violation> violations;

  public CentralChecker(@NonNull List<GameObject> parsedParameters) throws CheckerException {
    this.parsedParameters = parsedParameters;
    this.violations = List.of();
    this.reference = tryCheckBoard();
  }

  public @NonNull Board tryCheckBoard() throws ViolationsDetectedException {
    var boards =
        parsedParameters.stream()
            .filter(gameObject -> gameObject instanceof Board)
            .map(b -> (Board) b)
            .collect(Collectors.toList());

    violations = new BoardChecker().checkParameters(boards);
    if (violations.size() != 0) throw new ViolationsDetectedException(violations);
    return boards.get(0);
  }

  public @NonNull void checkParametersOrThrow() throws ViolationsDetectedException {
    violations.addAll(
        new AdventurerChecker(reference)
            .checkParameters(
                ListCastUtils.parameterAggregator(parsedParameters, Adventurer.class)));
    violations.addAll(
        new MountainChecker(reference)
            .checkParameters(ListCastUtils.parameterAggregator(parsedParameters, Mountain.class)));
    violations.addAll(
        new TreasureChecker(reference)
            .checkParameters(ListCastUtils.parameterAggregator(parsedParameters, Treasure.class)));

    if (violations.size() != 0) throw new ViolationsDetectedException(violations);
  }
}
