package fr.carbonit.checker.treasure;

import fr.carbonit.checker.Violation;
import fr.carbonit.model.Axes;
import fr.carbonit.model.parameters.Treasure;
import lombok.NonNull;

public class TreasureIllegalPositionViolation extends Violation {

  public TreasureIllegalPositionViolation(@NonNull Axes reason) {
    super(reason);
  }
}
