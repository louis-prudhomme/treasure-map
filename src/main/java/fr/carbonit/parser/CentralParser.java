package fr.carbonit.parser;

import fr.carbonit.model.objects.GameObject;
import fr.carbonit.model.objects.GameObjectHeadersEnum;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.parser.exception.SourceFileUnavalaibleException;
import lombok.NonNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CentralParser {
  @NonNull private final String path;

  @NonNull private final List<GameObject> parsedParameters;

  @NonNull
  private final Map<GameObjectHeadersEnum, AbstractGameObjectParser<? extends GameObject>>
      parserMap;

  public CentralParser(@NonNull String path) {
    this.path = path;
    this.parsedParameters = new ArrayList<>();
    this.parserMap =
        Map.ofEntries(
            Map.entry(GameObjectHeadersEnum.TREASURE, new TreasureParser()),
            Map.entry(GameObjectHeadersEnum.MOUNTAIN, new MountainParser()),
            Map.entry(GameObjectHeadersEnum.ADVENTURER, new AdventurerParser()),
            Map.entry(GameObjectHeadersEnum.BOARD, new BoardParser()));
  }

  public @NonNull List<GameObject> parseParameters() throws ParserException {
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String currentLine = reader.readLine();

      while (currentLine != null) {
        if (GameObjectHeadersEnum.isCharKeyOf(currentLine.charAt(0))) {
          var header = GameObjectHeadersEnum.get(currentLine.charAt(0));
          parsedParameters.add(parserMap.get(header).parseParameter(currentLine));
        }
        currentLine = reader.readLine();
      }

      return parsedParameters;
    } catch (FileNotFoundException e) {
      throw new SourceFileUnavalaibleException(e);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
