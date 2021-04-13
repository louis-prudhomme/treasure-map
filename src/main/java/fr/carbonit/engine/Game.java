package fr.carbonit.engine;

import fr.carbonit.model.Axes;
import fr.carbonit.model.exception.GameOutOfBoundsException;
import fr.carbonit.model.exception.NoSuchObjectException;
import fr.carbonit.model.exception.TileNotEmptyException;
import fr.carbonit.model.exception.UnremovableObjectException;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.model.objects.Treasure;
import fr.carbonit.utils.ListCastUtils;
import lombok.Data;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.carbonit.model.objects.GameObjectHeadersEnum.*;

@Data
public class Game {
  @NonNull private final Board underlying;
  @NonNull private final Axes dimension;
  @NonNull private final Queue<Treasure> stash;
  private final GameObject[][] map;

  public Game(@NonNull Board underlying) {
    this.underlying = underlying;
    this.dimension = underlying.getPosition();

    this.map = new GameObject[this.dimension.getY()][this.dimension.getX()];
    this.stash = new LinkedList<>();
  }

  public void addAll(@NonNull List<GameObject> gameObjects) {
    gameObjects.forEach(this::add);
  }

  public void add(@NonNull GameObject toAdd) {
    if (toAdd.getHeader() == BOARD) return;

    if (!dimension.isPositionWithin(toAdd.getPosition()))
      throw new GameOutOfBoundsException(toAdd.getPosition(), dimension);

    var tile = get(toAdd.getPosition());

    if (tile.isPresent())
      if (tile.get().getHeader() == TREASURE && toAdd.getHeader() == ADVENTURER)
        pickupTreasure(((Adventurer) toAdd), ((Treasure) tile.get()));
      else throw new TileNotEmptyException(toAdd.getPosition(), get(toAdd.getPosition()));

    map[toAdd.getPosition().getY()][toAdd.getPosition().getX()] = toAdd;
  }

  private void pickupTreasure(@NonNull Adventurer picker, @NonNull Treasure picked) {
    picker.pickupTreasure();

    picked.wasTaken();
    if (picked.getWorth() == 0) remove(picked);
    else stash.add(picked);
  }

  public @NonNull Optional<GameObject> get(@NonNull Axes position) {
    return Optional.ofNullable(map[position.getY()][position.getX()]);
  }

  public void remove(@NonNull GameObject object) {
    if (object.getHeader() == MOUNTAIN || object.getHeader() == BOARD)
      throw new UnremovableObjectException(object);

    if (!dimension.isPositionWithin(object.getPosition()))
      throw new GameOutOfBoundsException(object.getPosition(), dimension);

    if (get(object.getPosition()).map(tile -> tile != object).orElse(true))
      throw new NoSuchObjectException(object, get(object.getPosition()));

    map[object.getPosition().getY()][object.getPosition().getX()] = null;
    unstashTreasures();
  }

  private void unstashTreasures() {
    var toUnstash =
        stash.stream()
            .filter(treasure -> get(treasure.getPosition()).isEmpty())
            .collect(Collectors.toList());
    toUnstash.forEach(this::add);
    stash.removeAll(toUnstash);
  }

  public @NonNull List<GameObject> getGameObjects() {
    var gameObjects =
        Stream.concat(
                Arrays.stream(map).flatMap(Arrays::stream).filter(Objects::nonNull), stash.stream())
            .collect(Collectors.toList());
    gameObjects.add(0, underlying);
    return gameObjects;
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
