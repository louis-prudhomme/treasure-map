package fr.carbonit.checker.mountain;

import fr.carbonit.checker.Violation;
import fr.carbonit.model.Axes;
import lombok.NonNull;

public class MountainNotOnBoardViolation extends Violation {

  public MountainNotOnBoardViolation(@NonNull Axes board, @NonNull Axes mountain) {
    super(String.format("Board is %s, mountain is %s", board, mountain));
  }
}
