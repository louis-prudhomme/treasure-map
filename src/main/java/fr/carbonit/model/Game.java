package fr.carbonit.model;

import fr.carbonit.model.exception.GameOutOfBoundsException;
import fr.carbonit.model.parameters.Board;
import fr.carbonit.model.parameters.GameObject;
import lombok.Data;
import lombok.NonNull;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class Game {
  @NonNull private final Axes dimension;
  @NonNull private final GameObject[][] map;

  public Game(@NonNull Board dimension) {
    this.dimension = dimension.getPosition();
    // todo fix this
    this.map = new GameObject[this.dimension.getX()][this.dimension.getY()];
  }

  public void addGameObject(@NonNull GameObject object) {
    // todo gameOOB into runtime ?
    if (!dimension.isPositionWithin(object.getPosition()))
      throw new GameOutOfBoundsException(object.getPosition(), dimension);

    // todo if something already there
    map[object.getPosition().getX()][object.getPosition().getY()] = object;
  }

  @Override
  public String toString() {
    return Arrays.stream(map)
        .map(
            line ->
                Arrays.stream(line)
                    .map(gameObject -> gameObject == null ? "." : gameObject.toString())
                    .map(s -> String.format("%-10s", s))
                    .collect(Collectors.joining("\t")))
        .collect(Collectors.joining(System.lineSeparator()));
  }
}
