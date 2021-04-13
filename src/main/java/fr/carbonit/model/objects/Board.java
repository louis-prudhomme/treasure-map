package fr.carbonit.model.objects;

import fr.carbonit.model.Axes;
import lombok.NonNull;

public class Board extends GameObject {
  public Board(@NonNull Axes size) {
    super(GameObjectHeadersEnum.BOARD, size);
  }

  public boolean isPositionWithin(@NonNull Axes external) {
    return position.isPositionWithin(external);
  }
}
