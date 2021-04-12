package fr.carbonit.checker.adventurer;

import fr.carbonit.checker.Violation;
import fr.carbonit.model.Axes;
import lombok.NonNull;

public class AdventurerIllegalPositionViolation extends Violation {

  public AdventurerIllegalPositionViolation(@NonNull Axes reason) {
    super(reason);
  }
}
