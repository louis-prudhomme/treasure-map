package fr.carbonit;

import fr.carbonit.checker.CentralChecker;
import fr.carbonit.checker.exception.CheckerException;
import fr.carbonit.exception.ShouldNotHappenException;
import fr.carbonit.model.Game;
import fr.carbonit.model.ParameterHeadersEnum;
import fr.carbonit.model.parameters.Board;
import fr.carbonit.model.parameters.GameObject;
import fr.carbonit.parser.CentralParser;
import fr.carbonit.parser.exception.ParserException;

import java.util.List;

public class Main {
  private static CentralParser parser;
  private static CentralChecker checker;
  private static List<GameObject> parsed;
  private static Game game;

  public static void main(String[] args) {

    try {
      parser = new CentralParser(args[0]);
      parsed = parser.parseParameters();

      checker = new CentralChecker(parsed);
      checker.checkParametersOrThrow();

      game =
          new Game(
              (Board)
                  parsed.stream()
                      .filter(go -> go.getHeader() == ParameterHeadersEnum.BOARD)
                      .findFirst()
                      .orElseThrow(ShouldNotHappenException::new));
      parsed.stream()
          .filter(gameObject -> gameObject.getHeader() != ParameterHeadersEnum.BOARD)
          .forEach(gameObject -> game.addGameObject(gameObject));

      System.out.println(game);
    } catch (ParserException | CheckerException e) {
      e.printStackTrace();
    }
  }
}
