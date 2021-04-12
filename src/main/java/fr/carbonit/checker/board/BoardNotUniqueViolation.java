package fr.carbonit.checker.board;

import fr.carbonit.checker.Violation;
import lombok.NonNull;

public class BoardNotUniqueViolation extends Violation {

  public BoardNotUniqueViolation(@NonNull int reason) {
    super(reason);
  }
}
