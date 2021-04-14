package fr.carbonit.writer;

import fr.carbonit.engine.Game;
import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.*;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.List;

import static fr.carbonit.model.objects.GameObjectHeadersEnum.*;
import static fr.carbonit.model.objects.RotationEnum.NORD;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CentralWriterTest {
  private static final String EXPECTED =
      String.join(
          System.lineSeparator(),
          "Endgame:",
          "A - name - 0 - 0 - N - 0",
          "C - 0 - 0",
          "M - 0 - 0",
          "T - 0 - 0 - 0",
          "Map:",
          "game");

  private static Game mockedGame = mock(Game.class);

  private Adventurer mockedAdventurer = mock(Adventurer.class);
  private Board mockedBoard = mock(Board.class);
  private Mountain mockedMoutain = mock(Mountain.class);
  private Treasure mockedTreasure = mock(Treasure.class);

  private Axes mockedAxes = mock(Axes.class);

  private List<GameObject> listOfMocks;

  private CentralWriter toTest;
  @Getter private StringWriter stringWriter;

  @BeforeEach
  public void init() {
    initGameObjectMocks();
    listOfMocks = List.of(mockedAdventurer, mockedBoard, mockedMoutain, mockedTreasure);
    when(mockedGame.getGameObjects()).thenReturn(listOfMocks);
    when(mockedGame.toString()).thenReturn("game");

    stringWriter = new StringWriter();
    toTest = new CentralWriter(mockedGame, this::getStringWriter);
  }

  private void initGameObjectMocks() {
    when(mockedAxes.getX()).thenReturn(0);
    when(mockedAxes.getY()).thenReturn(0);

    when(mockedAdventurer.getHeader()).thenReturn(ADVENTURER);
    when(mockedAdventurer.getPosition()).thenReturn(mockedAxes);
    when(mockedAdventurer.getRotation()).thenReturn(NORD);
    when(mockedAdventurer.getName()).thenReturn("name");
    when(mockedAdventurer.getNumberOfTreasures()).thenReturn(0);

    when(mockedBoard.getHeader()).thenReturn(BOARD);
    when(mockedBoard.getPosition()).thenReturn(mockedAxes);

    when(mockedMoutain.getHeader()).thenReturn(MOUNTAIN);
    when(mockedMoutain.getPosition()).thenReturn(mockedAxes);

    when(mockedTreasure.getHeader()).thenReturn(TREASURE);
    when(mockedTreasure.getPosition()).thenReturn(mockedAxes);
    when(mockedTreasure.getWorth()).thenReturn(0);
  }

  @SneakyThrows
  @Test
  public void writerGoesThroughListAndPrintsGameTest() {
    toTest.redactReport();
    assert (StringUtils.difference(stringWriter.toString(), EXPECTED).equals(""));
  }
}
