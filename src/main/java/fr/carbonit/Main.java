package fr.carbonit;

import fr.carbonit.checker.CentralChecker;
import fr.carbonit.checker.exception.CheckerException;
import fr.carbonit.engine.Game;
import fr.carbonit.engine.GameEngine;
import fr.carbonit.exception.ShouldNotHappenException;
import fr.carbonit.exception.TreasureMapProgramRuntimeException;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.parser.CentralParser;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.utils.ListCastUtils;
import fr.carbonit.writer.CentralWriter;
import lombok.NonNull;

import java.io.*;
import java.time.Instant;
import java.util.List;

public class Main {
  public static final String OUTPUT_FILENAME = Instant.now().getEpochSecond() + ".report";
  public static String input_filename;

  public static void main(@NonNull String[] args) {
    try {
      input_filename = args[0];
      CentralParser parser = new CentralParser(Main::getReader);
      List<GameObject> parsed = parser.parseParameters();

      CentralChecker checker = new CentralChecker(parsed);
      checker.checkParametersOrThrow();

      Game game =
          new Game(
              ListCastUtils.findFirstOfType(parsed, Board.class)
                  .orElseThrow(ShouldNotHappenException::new));
      game.addAll(parsed);

      GameEngine engine = new GameEngine(game);
      engine.playGame();

      CentralWriter writer = new CentralWriter(game, Main::getWriter);
      writer.redactReport();

      System.out.println(game);
    } catch (ParserException | CheckerException | IOException | UncheckedIOException e) {
      throw new TreasureMapProgramRuntimeException(e);
    } catch (Exception e) {
      throw new ShouldNotHappenException(e);
    }
  }

  public static Writer getWriter() {
    try {
      return new FileWriter(OUTPUT_FILENAME);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static Reader getReader() {
    try {
      return new FileReader(input_filename);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
