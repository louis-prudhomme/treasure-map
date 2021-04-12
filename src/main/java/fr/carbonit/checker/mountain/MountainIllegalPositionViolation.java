package fr.carbonit.checker.mountain;

import fr.carbonit.checker.Violation;
import fr.carbonit.model.Axes;
import lombok.NonNull;

public class MountainIllegalPositionViolation extends Violation {

  public MountainIllegalPositionViolation(@NonNull Axes reason) {
    super(reason);
  }
}
