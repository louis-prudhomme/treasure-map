package fr.carbonit.checker.treasure;

import fr.carbonit.checker.AbstractGameObjectChecker;
import fr.carbonit.checker.PositionChecker;
import fr.carbonit.checker.Violation;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.Treasure;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class TreasureChecker extends AbstractGameObjectChecker<Treasure> {
  @NonNull private final Board referenceBoard;

  @Override
  public @NonNull List<Function<Treasure, Violation>> getChecks() {
    return List.of(
        this::isTreasurePositionLegal, this::isTreasureValueLegal, this::isTreasureOnBoard);
  }

  public Violation isTreasurePositionLegal(@NonNull Treasure treasure) {
    return PositionChecker.isLegalPosition(treasure.getPosition())
        ? null
        : new TreasureIllegalPositionViolation(treasure.getPosition());
  }

  public Violation isTreasureValueLegal(@NonNull Treasure treasure) {
    return treasure.getWorth() > 0 ? null : new TreasureIllegalValueViolation(treasure.getWorth());
  }

  public Violation isTreasureOnBoard(@NonNull Treasure treasure) {
    return referenceBoard.isPositionWithin(treasure.getPosition())
        ? null
        : new TreasureNotOnBoardViolation(referenceBoard.getPosition(), treasure.getPosition());
  }
}
