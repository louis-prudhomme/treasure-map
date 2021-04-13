package fr.carbonit.checker.treasure;

import fr.carbonit.checker.Violation;
import fr.carbonit.model.Axes;
import lombok.NonNull;

public class TreasureNotOnBoardViolation extends Violation {

  public TreasureNotOnBoardViolation(@NonNull Axes board, @NonNull Axes treasure) {
    super(String.format("Board is %s, treasure is %s", board, treasure));
  }
}
