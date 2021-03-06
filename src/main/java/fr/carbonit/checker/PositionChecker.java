package fr.carbonit.checker;

import fr.carbonit.model.Axes;
import lombok.NonNull;

public class PositionChecker {
  public static boolean isLegalPosition(@NonNull Axes position) {
    return position.getX() >= 0 && position.getY() >= 0;
  }

  public static boolean isLegalDimension(@NonNull Axes position) {
    return position.getX() > 0 && position.getY() > 0;
  }
}
