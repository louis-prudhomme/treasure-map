package fr.carbonit.checker.adventurer;

import fr.carbonit.checker.Violation;
import fr.carbonit.model.Axes;
import lombok.NonNull;

public class AdventurerNotOnBoardViolation extends Violation {

  public AdventurerNotOnBoardViolation(@NonNull Axes board, @NonNull Axes adventurer) {
    super(String.format("Board is %s, adventurer is %s", board, adventurer));
  }
}
