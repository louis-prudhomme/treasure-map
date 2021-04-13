package fr.carbonit.model;

import fr.carbonit.model.exception.GameOutOfBoundsException;
import fr.carbonit.model.exception.NoSuchObjectException;
import fr.carbonit.model.exception.TileNotEmptyException;
import fr.carbonit.model.exception.UnremovableObjectException;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.model.objects.Treasure;
import lombok.Data;
import lombok.NonNull;
import utils.ListCastUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Game {
  @NonNull private final Axes dimension;
  private final GameObject[][] map;

  public Game(@NonNull Board dimension) {
    this.dimension = dimension.getPosition();
    // todo fix this
    this.map = new GameObject[this.dimension.getX()][this.dimension.getY()];
  }

  public void addAll(@NonNull List<GameObject> objectList) {
    objectList.forEach(this::add);
  }

  public void add(@NonNull GameObject object) {
    if (object.getHeader() == ParameterHeadersEnum.BOARD) return;

    if (!dimension.isPositionWithin(object.getPosition()))
      throw new GameOutOfBoundsException(object.getPosition(), dimension);

    var tile = get(object.getPosition());
    if (tile.isPresent()
        && tile.get().getHeader() == ParameterHeadersEnum.TREASURE
        && object.getHeader() == ParameterHeadersEnum.ADVENTURER) {
      ((Treasure) tile.get()).wasTaken();
      ((Adventurer) object).pickupTreasure();
    } else if (tile.isPresent())
      throw new TileNotEmptyException(object.getPosition(), get(object.getPosition()));

    // todo do not erase treasures
    map[object.getPosition().getX()][object.getPosition().getY()] = object;
  }

  public @NonNull Optional<GameObject> get(@NonNull Axes position) {
    if (!dimension.isPositionWithin(position)) return Optional.empty();
    return Optional.ofNullable(map[position.getX()][position.getY()]);
  }

  public void remove(@NonNull GameObject object) {
    if (object.getHeader() == ParameterHeadersEnum.MOUNTAIN
        || object.getHeader() == ParameterHeadersEnum.BOARD)
      throw new UnremovableObjectException(object);

    if (!dimension.isPositionWithin(object.getPosition()))
      throw new GameOutOfBoundsException(object.getPosition(), dimension);

    var t = get(object.getPosition());
    var d = get(object.getPosition()).map(tile -> tile == object);
    var f = get(object.getPosition()).map(tile -> tile == object).orElse(false);
    if (get(object.getPosition()).map(tile -> tile != object).orElse(true))
      throw new NoSuchObjectException(object, get(object.getPosition()));

    map[object.getPosition().getX()][object.getPosition().getY()] = null;
  }

  public @NonNull List<Adventurer> getAdventurerRegistry() {
    return ListCastUtils.parameterAggregator(
        Arrays.stream(map)
            .flatMap(Arrays::stream)
            .filter(Objects::nonNull)
            .collect(Collectors.toList()),
        Adventurer.class);
  }

  @Override
  public String toString() {
    return Arrays.stream(map)
        .map(
            line ->
                Arrays.stream(line)
                    .map(Optional::ofNullable)
                    .map(gameObject -> gameObject.map(GameObject::toString).orElse("."))
                    .map(s -> String.format("%-8s", s))
                    .collect(Collectors.joining("\t")))
        .collect(Collectors.joining(System.lineSeparator()));
  }
}
