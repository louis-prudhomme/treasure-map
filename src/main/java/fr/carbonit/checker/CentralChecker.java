package fr.carbonit.checker;

import fr.carbonit.checker.adventurer.AdventurerChecker;
import fr.carbonit.checker.board.BoardChecker;
import fr.carbonit.checker.exception.CheckerException;
import fr.carbonit.checker.exception.ViolationsDetectedException;
import fr.carbonit.checker.mountain.MountainChecker;
import fr.carbonit.checker.treasure.TreasureChecker;
import fr.carbonit.model.parameters.*;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CentralChecker {
  @NonNull private final List<GameObject> parsedParameters;
  @NonNull private final Map<Class<? extends GameObject>, AbstractParameterChecker<?>> checkerMap;
  @NonNull private List<Violation> violations;
  @NonNull private Board reference;

  public CentralChecker(@NonNull List<GameObject> parsedParameters) throws CheckerException {
    this.parsedParameters = parsedParameters;
    this.reference = tryCheckBoard();
    checkerMap = Map.ofEntries(Map.entry(Mountain.class, new MountainChecker(reference)));
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

  public @NonNull List<Violation> checkParameters() throws ViolationsDetectedException {
    violations.addAll(
        new AdventurerChecker(reference).checkParameters(parameterAggregator(Adventurer.class)));
    violations.addAll(
        new MountainChecker(reference).checkParameters(parameterAggregator(Mountain.class)));
    violations.addAll(
        new TreasureChecker(reference).checkParameters(parameterAggregator(Treasure.class)));

    if (violations.size() != 0) throw new ViolationsDetectedException(violations);
    return violations;
  }

  private @NonNull <T extends GameObject> List<T> parameterAggregator(@NonNull Class<T> clazz) {
    return parsedParameters.stream()
        .filter(gameObject -> gameObject.getClass().equals(clazz))
        .map(clazz::cast)
        .collect(Collectors.toList());
  }
}
