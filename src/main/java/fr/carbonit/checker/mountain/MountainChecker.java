package fr.carbonit.checker.mountain;

import fr.carbonit.checker.AbstractGameObjectChecker;
import fr.carbonit.checker.PositionChecker;
import fr.carbonit.checker.Violation;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.Mountain;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class MountainChecker extends AbstractGameObjectChecker<Mountain> {
  @NonNull private final Board referenceBoard;

  @Override
  public @NonNull List<Function<Mountain, Violation>> getChecks() {
    return List.of(this::isMountainPositionLegal, this::isMountainOnBoard);
  }

  private Violation isMountainPositionLegal(@NonNull Mountain mountain) {
    return PositionChecker.isLegalPosition(mountain.getPosition())
        ? null
        : new MountainIllegalPositionViolation(mountain.getPosition());
  }

  public Violation isMountainOnBoard(@NonNull Mountain mountain) {
    return referenceBoard.isPositionWithin(mountain.getPosition())
        ? null
        : new MountainNotOnBoardViolation(referenceBoard.getPosition(), mountain.getPosition());
  }
}
