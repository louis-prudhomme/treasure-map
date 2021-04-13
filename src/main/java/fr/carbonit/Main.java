package fr.carbonit;

import fr.carbonit.checker.CentralChecker;
import fr.carbonit.checker.exception.CheckerException;
import fr.carbonit.engine.GameEngine;
import fr.carbonit.exception.ShouldNotHappenException;
import fr.carbonit.model.Game;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.parser.CentralParser;
import fr.carbonit.parser.exception.ParserException;
import utils.ListCastUtils;

import java.util.List;

public class Main {
  private static CentralParser parser;
  private static CentralChecker checker;
  private static List<GameObject> parsed;
  private static Game game;
  private static GameEngine engine;

  public static void main(String[] args) {

    try {
      parser = new CentralParser(args[0]);
      parsed = parser.parseParameters();

      checker = new CentralChecker(parsed);
      checker.checkParametersOrThrow();

      game =
          new Game(
              ListCastUtils.findFirstOfType(parsed, Board.class)
                  .orElseThrow(ShouldNotHappenException::new));
      game.addAll(parsed);

      System.out.println(game);
      engine = new GameEngine(game);
      engine.playGame();
      System.out.println(game);
    } catch (ParserException | CheckerException e) {
      e.printStackTrace();
    }
  }
}
