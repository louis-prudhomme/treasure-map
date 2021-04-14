package fr.carbonit.engine;

import fr.carbonit.model.Axes;
import fr.carbonit.model.exception.GameOutOfBoundsException;
import fr.carbonit.model.exception.TileNotEmptyException;
import fr.carbonit.model.objects.*;
import fr.carbonit.utils.Pair;
import lombok.Getter;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;

import static fr.carbonit.model.objects.GameObjectHeadersEnum.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GameTest {
  @Getter
  private static final List<Class<? extends GameObject>> illegalPositionToMock =
      List.of(Mountain.class, Adventurer.class, Treasure.class);

  @Getter
  private static final Set<Pair<Class<? extends GameObject>, GameObjectHeadersEnum>>
      tileNotEmptyContentsAndHeader =
          Set.of(Pair.of(Mountain.class, MOUNTAIN), Pair.of(Adventurer.class, ADVENTURER));

  private Game game;
  private Board mockedBoard = mock(Board.class);
  private Adventurer mockedAdventurer = mock(Adventurer.class);

  private Axes mockedDimension = mock(Axes.class);
  private Axes mockedPositionLegal = mock(Axes.class);
  private Axes mockedPositionIllegal = mock(Axes.class);

  @BeforeEach
  public void init() {
    reset(mockedDimension, mockedBoard);

    when(mockedDimension.getX()).thenReturn(1);
    when(mockedDimension.getY()).thenReturn(1);

    when(mockedPositionLegal.getX()).thenReturn(0);
    when(mockedPositionLegal.getY()).thenReturn(0);

    when(mockedPositionIllegal.getX()).thenReturn(-1);
    when(mockedPositionIllegal.getY()).thenReturn(-1);

    when(mockedBoard.getPosition()).thenReturn(mockedDimension);

    game = new Game(mockedBoard);
  }

  @Test
  public void addBoardDoesNothingTest() {
    when(mockedBoard.getHeader()).thenReturn(BOARD);
    game.add(mockedBoard);
    verify(mockedDimension, times(0)).isPositionWithin(any());
  }

  @ParameterizedTest
  @MethodSource("getIllegalPositionToMock")
  public void addGameObjectWithIllegalPositionThrowsTest(
      @NonNull Class<? extends GameObject> toCreateMockOf) {

    var illegalPositionMock = mock(toCreateMockOf);
    when(illegalPositionMock.getPosition()).thenReturn(mockedPositionIllegal);
    when(mockedDimension.isPositionWithin(mockedPositionIllegal)).thenReturn(false);

    assertThrows(GameOutOfBoundsException.class, () -> game.add(illegalPositionMock));
  }

  @ParameterizedTest
  @MethodSource("getTileNotEmptyContentsAndHeader")
  public void tileNotEmptyThrowsWhenNotTreasureTest(
      @NonNull Pair<Class<? extends GameObject>, GameObjectHeadersEnum> toMockExpected) {
    var mockTileContent = mock(toMockExpected.getA());
    when(mockTileContent.getPosition()).thenReturn(mockedPositionLegal);
    when(mockTileContent.getHeader()).thenReturn(toMockExpected.getB());

    when(mockedDimension.isPositionWithin(any())).thenReturn(true);

    when(mockedAdventurer.getPosition()).thenReturn(mockedPositionLegal);
    when(mockedAdventurer.getHeader()).thenReturn(ADVENTURER);

    game.add(mockTileContent);
    assertThrows(TileNotEmptyException.class, () -> game.add(mockedAdventurer));
  }

  @Test
  public void tileNotEmptyDoesntThrowWhenTreasureTest() {
    var mockedTreasureTile = mock(Treasure.class);
    when(mockedTreasureTile.getPosition()).thenReturn(mockedPositionLegal);
    when(mockedTreasureTile.getHeader()).thenReturn(TREASURE);

    when(mockedDimension.isPositionWithin(any())).thenReturn(true);

    when(mockedAdventurer.getPosition()).thenReturn(mockedPositionLegal);
    when(mockedAdventurer.getHeader()).thenReturn(ADVENTURER);

    game.add(mockedTreasureTile);
    game.add(mockedAdventurer);

    verify(mockedAdventurer, times(1)).pickupTreasure();
    verify(mockedTreasureTile, times(1)).wasTaken();
  }

  @Test
  public void treasureEmptyIsRemovedTest() {
    var mockedTreasureTile = mock(Treasure.class);
    when(mockedTreasureTile.getPosition()).thenReturn(mockedPositionLegal);
    when(mockedTreasureTile.getHeader()).thenReturn(TREASURE);
    when(mockedTreasureTile.getWorth()).thenReturn(0);

    when(mockedDimension.isPositionWithin(any())).thenReturn(true);

    when(mockedAdventurer.getPosition()).thenReturn(mockedPositionLegal);
    when(mockedAdventurer.getHeader()).thenReturn(ADVENTURER);

    game.add(mockedTreasureTile);
    game.add(mockedAdventurer);

    verify(mockedAdventurer, times(1)).pickupTreasure();
    verify(mockedTreasureTile, times(1)).wasTaken();

    assert (game.getStash().size() == 0);
  }

  @Test
  public void treasureNotEmptyIsStashedTest() {
    var mockedTreasureTile = mock(Treasure.class);
    when(mockedTreasureTile.getPosition()).thenReturn(mockedPositionLegal);
    when(mockedTreasureTile.getHeader()).thenReturn(TREASURE);
    when(mockedTreasureTile.getWorth()).thenReturn(1);

    when(mockedDimension.isPositionWithin(any())).thenReturn(true);

    when(mockedAdventurer.getPosition()).thenReturn(mockedPositionLegal);
    when(mockedAdventurer.getHeader()).thenReturn(ADVENTURER);

    game.add(mockedTreasureTile);
    game.add(mockedAdventurer);

    verify(mockedAdventurer, times(1)).pickupTreasure();
    verify(mockedTreasureTile, times(1)).wasTaken();

    assert (game.getStash().size() == 1);
    assert (game.getStash().peek() == mockedTreasureTile);
  }
}
