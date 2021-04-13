package fr.carbonit.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Axes {
  private final int y;
  private final int x;

  public boolean isPositionWithin(@NonNull Axes external) {
    return external.getY() < y && external.getX() < x;
  }

  public Axes moveXWithin(int qte, int bound) {
    return new Axes(Math.floorMod(y + qte, bound), x);
  }

  public Axes moveYWithin(int qte, int bound) {
    return new Axes(y, Math.floorMod(x + qte, bound));
  }
}
