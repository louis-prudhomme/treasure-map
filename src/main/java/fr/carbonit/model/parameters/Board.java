package fr.carbonit.model.parameters;

import fr.carbonit.model.Axes;
import fr.carbonit.model.exception.GameOutOfBoundsException;
import lombok.Getter;
import lombok.NonNull;

public class Board implements GameObject {
  @NonNull @Getter private final Axes position;
  @NonNull private final GameObject[][] map;

  public Board(@NonNull Axes size) {
    this.position = size;
    this.map = new GameObject[size.getX()][size.getY()];
  }

  public void addGameObject(@NonNull GameObject object) throws GameOutOfBoundsException {
    // todo gameOOB into runtime ?
    if (!isPositionWithin(object.getPosition()))
      throw new GameOutOfBoundsException(object.getPosition(), position);

    map[object.getPosition().getX()][object.getPosition().getY()] = object;
  }

  public boolean isPositionWithin(@NonNull Axes external) {
    return position.getX() >= external.getX() || position.getY() >= external.getY();
  }
}
