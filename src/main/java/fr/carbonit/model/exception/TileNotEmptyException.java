package fr.carbonit.model.exception;

import fr.carbonit.model.Axes;
import lombok.NonNull;

public class TileNotEmptyException extends RuntimeException {
  private static final String TEXT = "Tile %s already contains %s";

  public TileNotEmptyException(@NonNull Axes position, @NonNull Object inside) {
    super(String.format(TEXT, position, inside));
  }
}
