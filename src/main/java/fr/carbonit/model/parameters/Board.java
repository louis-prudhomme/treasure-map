package fr.carbonit.model.parameters;

import fr.carbonit.model.Axes;
import fr.carbonit.model.ParameterHeadersEnum;
import lombok.NonNull;

public class Board extends GameObject {
  public Board(@NonNull Axes size) {
    super(size, ParameterHeadersEnum.BOARD);
  }

  public boolean isPositionWithin(@NonNull Axes external) {
    return position.getX() >= external.getX() || position.getY() >= external.getY();
  }
}
