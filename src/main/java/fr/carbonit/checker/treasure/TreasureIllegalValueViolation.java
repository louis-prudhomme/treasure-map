package fr.carbonit.checker.treasure;

import fr.carbonit.checker.Violation;
import lombok.NonNull;

public class TreasureIllegalValueViolation extends Violation {

  public TreasureIllegalValueViolation(@NonNull int reason) {
    super(reason);
  }
}
