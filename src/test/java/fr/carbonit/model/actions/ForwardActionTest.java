package fr.carbonit.model.actions;

import fr.carbonit.engine.Game;
import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.model.objects.GameObjectHeadersEnum;
import fr.carbonit.model.objects.RotationEnum;
import fr.carbonit.utils.Pair;
import lombok.Getter;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.Set;

import static fr.carbonit.model.objects.GameObjectHeadersEnum.*;
import static fr.carbonit.model.objects.RotationEnum.*;
import static org.mockito.Mockito.*;

public class ForwardActionTest {
  @Getter
  private static final Set<Pair<RotationEnum, Axes>> adventurerRotationsToExpected =
      Set.of(
          new Pair<>(NORD, new Axes(0, 1)),
          new Pair<>(SUD, new Axes(2, 1)),
          new Pair<>(EST, new Axes(1, 2)),
          new Pair<>(OUEST, new Axes(1, 0)));

  @Getter
  private static final Set<Pair<RotationEnum, Axes>> adventurerRotationsAndPositions =
      Set.of(
          new Pair<>(NORD, new Axes(0, 1)),
          new Pair<>(SUD, new Axes(2, 1)),
          new Pair<>(EST, new Axes(1, 2)),
          new Pair<>(OUEST, new Axes(1, 0)));

  @Getter
  private static final Set<Pair<GameObjectHeadersEnum, Boolean>> tileContentAndExpected =
      Set.of(
          new Pair<>(ADVENTURER, false),
          new Pair<>(BOARD, false),
          new Pair<>(MOUNTAIN, false),
          new Pair<>(TREASURE, true));

  private static Axes dimension = new Axes(3, 3);
  private static Axes position = new Axes(1, 1);

  private static Game mockedGame = mock(Game.class);
  private static Adventurer mockedAdventurer = mock(Adventurer.class);
  private static GameObject mockedTile = mock(GameObject.class);

  private static ForwardAction action;
  private int count;

  @BeforeEach
  public void init() {
    reset(mockedAdventurer, mockedGame);

    when(mockedGame.getDimension()).thenReturn(dimension);
    when(mockedAdventurer.getPosition()).thenReturn(position);

    action = new ForwardAction();
  }

  @ParameterizedTest
  @MethodSource("getAdventurerRotationsToExpected")
  public void adventurerMovesCorrectlyDependingOnOrientation(
      @NonNull Pair<RotationEnum, Axes> rotationAndExpected) {
    when(mockedAdventurer.getRotation()).thenReturn(rotationAndExpected.getA());

    action.applyTo(mockedGame, mockedAdventurer);
    verify(mockedGame, times(1)).remove(mockedAdventurer);
    verify(mockedAdventurer, times(1)).setPosition(rotationAndExpected.getB());
    verify(mockedGame, times(1)).add(mockedAdventurer);
  }

  @ParameterizedTest
  @MethodSource("getAdventurerRotationsAndPositions")
  public void adventurerDoesntMoveWhenNearBoardLimit(
      @NonNull Pair<RotationEnum, Axes> rotationAndPositions) {
    when(mockedAdventurer.getRotation()).thenReturn(rotationAndPositions.getA());
    when(mockedAdventurer.getPosition()).thenReturn(rotationAndPositions.getB());

    action.applyTo(mockedGame, mockedAdventurer);
    verify(mockedGame, times(0)).remove(mockedAdventurer);
    verify(mockedAdventurer, times(0)).setPosition(rotationAndPositions.getB());
    verify(mockedGame, times(0)).add(mockedAdventurer);
  }

  @ParameterizedTest
  @MethodSource("getTileContentAndExpected")
  public void adventurerMovesAccordinglyWhenDestinationNotTreasure(
      @NonNull Pair<GameObjectHeadersEnum, Boolean> tileContentAndExpected) {
    when(mockedTile.getHeader()).thenReturn(tileContentAndExpected.getA());
    when(mockedGame.get(any())).thenReturn(Optional.ofNullable(mockedTile));

    assert (action.canMoveIn(mockedGame, mockedAdventurer.getPosition())
        == tileContentAndExpected.getB());
  }

  @ParameterizedTest
  @MethodSource("getTileContentAndExpected")
  public void adventurerMovesWhenDestinationEmpty(
      @NonNull Pair<GameObjectHeadersEnum, Boolean> tileContentAndExpected) {
    when(mockedTile.getHeader()).thenReturn(tileContentAndExpected.getA());
    when(mockedGame.get(any())).thenReturn(Optional.empty());

    assert (action.canMoveIn(mockedGame, mockedAdventurer.getPosition()));
  }
}
