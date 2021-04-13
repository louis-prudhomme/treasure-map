package fr.carbonit.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Axes {
  private final int x;
  private final int y;

  public boolean isPositionWithin(@NonNull Axes external) {
    return external.getX() < x
        && external.getY() < y
        && external.getX() >= 0
        && external.getY() >= 0;
  }

  public Axes moveXWithin(int qte, int bound) {
    return new Axes(Math.floorMod(x + qte, bound), y);
  }

  public Axes moveYWithin(int qte, int bound) {
    return new Axes(x, Math.floorMod(y + qte, bound));
  }
}
