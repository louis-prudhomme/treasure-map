package fr.carbonit.model.exception;

import fr.carbonit.model.Axes;
import lombok.NonNull;

public class GameOutOfBoundsException extends RuntimeException {
  private static final String TEXT = "Position %s not within %s";

  public GameOutOfBoundsException(@NonNull Axes position, @NonNull Axes bounds) {
    super(String.format(TEXT, position, bounds));
  }
}
