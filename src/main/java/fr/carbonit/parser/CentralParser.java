package fr.carbonit.parser;

import fr.carbonit.model.ParameterHeadersEnum;
import fr.carbonit.model.parameters.GameObject;
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
  private final Map<ParameterHeadersEnum, AbstractParameterParser<? extends GameObject>> parserMap;

  public CentralParser(@NonNull String path) {
    this.path = path;
    this.parsedParameters = new ArrayList<>();
    this.parserMap =
        Map.ofEntries(
            Map.entry(ParameterHeadersEnum.TREASURE, new TreasureParser()),
            Map.entry(ParameterHeadersEnum.MOUNTAIN, new MountainParser()),
            Map.entry(ParameterHeadersEnum.ADVENTURER, new AdventurerParser()),
            Map.entry(ParameterHeadersEnum.BOARD, new BoardParser()));
  }

  public @NonNull List<GameObject> parseParameters() throws ParserException {
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String currentLine = reader.readLine();

      while (currentLine != null) {
        if (ParameterHeadersEnum.isCharKeyOf(currentLine.charAt(0))) {
          var header = ParameterHeadersEnum.get(currentLine.charAt(0));
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
