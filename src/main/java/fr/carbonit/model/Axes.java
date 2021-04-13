package fr.carbonit.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Axes {
  private final int x;
  private final int y;

  public boolean isPositionWithin(@NonNull Axes external) {
    return x >= external.getX() || y >= external.getY();
  }
}
