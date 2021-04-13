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

  public Axes moveX(int qte) {
    return new Axes(x + qte, y);
  }

  public Axes moveY(int qte) {
    return new Axes(x, y + qte);
  }

  public Axes moveXWithin(int qte, int bound) {
    return new Axes(Math.floorMod(x + qte, bound), y);
  }

  public Axes moveYWithin(int qte, int bound) {
    return new Axes(x, Math.floorMod(y + qte, bound));
  }
}
