package fr.carbonit;

import fr.carbonit.checker.CentralChecker;
import fr.carbonit.checker.exception.CheckerException;
import fr.carbonit.engine.Game;
import fr.carbonit.engine.GameEngine;
import fr.carbonit.exception.ShouldNotHappenException;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.parser.CentralParser;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.utils.ListCastUtils;
import fr.carbonit.writer.CentralWriter;
import lombok.NonNull;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class Main {

  public static void main(@NonNull String[] args) {
    try {
      CentralParser parser = new CentralParser(args[0]);
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

      CentralWriter writer = new CentralWriter(Instant.now().getEpochSecond() + ".report", game);
      writer.redactReport();
      System.out.println(game);
    } catch (ParserException | CheckerException | IOException e) {
      throw new ShouldNotHappenException(e);
    }
  }
}
