package fr.carbonit.writer;

import fr.carbonit.engine.Game;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.model.objects.GameObjectHeadersEnum;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class CentralWriter {
  @NonNull
  private static final Map<GameObjectHeadersEnum, AbstractGameObjectWriter<?>> writerMap =
      Map.ofEntries(
          Map.entry(GameObjectHeadersEnum.ADVENTURER, new AdventurerWriter()),
          Map.entry(GameObjectHeadersEnum.TREASURE, new TreasureWriter()),
          Map.entry(GameObjectHeadersEnum.MOUNTAIN, new MountainWriter()),
          Map.entry(GameObjectHeadersEnum.BOARD, new BoardWriter()));

  @NonNull private final Game game;
  @NonNull private final Supplier<Writer> writerSupplier;

  private Writer report;

  public void redactReport() throws IOException {
    report = writerSupplier.get();

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
