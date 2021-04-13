package fr.carbonit.writer;

import fr.carbonit.engine.Game;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.model.objects.ParameterHeadersEnum;
import lombok.NonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CentralWriter {
  @NonNull private static final String PATH = "";

  @NonNull
  private static final Map<ParameterHeadersEnum, AbstractGameObjectWriter<?>> writerMap =
      Map.ofEntries(
          Map.entry(ParameterHeadersEnum.ADVENTURER, new AdventurerWriter()),
          Map.entry(ParameterHeadersEnum.TREASURE, new TreasureWriter()),
          Map.entry(ParameterHeadersEnum.MOUNTAIN, new MountainWriter()),
          Map.entry(ParameterHeadersEnum.BOARD, new BoardWriter()));
  @NonNull private final String fileName;
  @NonNull private final Game game;
  @NonNull private FileWriter report;

  public CentralWriter(@NonNull String fileName, @NonNull Game game) {
    this.fileName = fileName;
    this.game = game;
  }

  public void redactReport() throws IOException {
    report = new FileWriter(PATH + fileName);
    writeLineToFile("Endgame:");
    for (GameObject go : game.getGameObjects()) {
      writeLineToFile(writerMap.get(go.getHeader()).writeUncast(go));
    }
    writeLineToFile("Map:");
    writeLineToFile(game.toString());
    close();
  }

  private void close() throws IOException {
    this.report.flush();
    this.report.close();
  }

  private void writeLineToFile(@NonNull String line) throws IOException {
    this.report.write(line);
    this.report.write(System.lineSeparator());
  }
}