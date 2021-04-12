package fr.carbonit.checker.adventurer;

import fr.carbonit.checker.AbstractParameterChecker;
import fr.carbonit.checker.PositionChecker;
import fr.carbonit.checker.Violation;
import fr.carbonit.model.parameters.Adventurer;
import fr.carbonit.model.parameters.Board;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@AllArgsConstructor
public class AdventurerChecker extends AbstractParameterChecker<Adventurer> {
  private final Set<String> names = new HashSet<>();

  @NonNull private final Board referenceBoard;

  @Override
  public @NonNull List<Function<Adventurer, Violation>> getChecks() {
    return List.of(
        this::isAdventurerPositionLegal, this::isAdventurerNameUnique, this::isAdventurerOnBoard);
  }

  public Violation isAdventurerPositionLegal(@NonNull Adventurer adventurer) {
    return PositionChecker.isLegalPosition(adventurer.getPosition())
        ? null
        : new AdventurerIllegalPositionViolation(adventurer.getPosition());
  }

  public Violation isAdventurerNameUnique(@NonNull Adventurer adventurer) {
    if (names.contains(adventurer.getName()))
      return new AdventurerNameNotUniqueViolation(adventurer.getName());

    names.add(adventurer.getName());
    return null;
  }

  public Violation isAdventurerOnBoard(@NonNull Adventurer adventurer) {
    return referenceBoard.isPositionWithin(adventurer.getPosition())
        ? null
        : new AdventurerNotOnBoardViolation(referenceBoard.getPosition(), adventurer.getPosition());
  }
}
