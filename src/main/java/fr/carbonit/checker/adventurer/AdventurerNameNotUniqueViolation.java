package fr.carbonit.checker.adventurer;

import fr.carbonit.checker.Violation;
import lombok.NonNull;

public class AdventurerNameNotUniqueViolation extends Violation {

  public AdventurerNameNotUniqueViolation(@NonNull String reason) {
    super(reason);
  }
}
