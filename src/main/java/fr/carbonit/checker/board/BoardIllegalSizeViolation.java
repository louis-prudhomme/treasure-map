package fr.carbonit.checker.board;

import fr.carbonit.checker.Violation;
import fr.carbonit.model.Axes;
import lombok.NonNull;

public class BoardIllegalSizeViolation extends Violation {

  public BoardIllegalSizeViolation(@NonNull Axes reason) {
    super(reason);
  }
}
