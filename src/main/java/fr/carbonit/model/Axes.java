package fr.carbonit.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Axes {
  private final int y;
  private final int x;

  public boolean isPositionWithin(@NonNull Axes external) {
    return external.getY() < y
        && external.getX() < x
        && external.getX() >= 0
        && external.getY() >= 0;
  }

  public Axes moveY(int qte) {
    return new Axes(y + qte, x);
  }

  public Axes moveX(int qte) {
    return new Axes(y, x + qte);
  }
}
