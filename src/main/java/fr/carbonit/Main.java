package fr.carbonit;

import fr.carbonit.checker.CentralChecker;
import fr.carbonit.checker.exception.CheckerException;
import fr.carbonit.model.parameters.GameObject;
import fr.carbonit.parser.CentralParser;
import fr.carbonit.parser.exception.ParserException;

import java.util.List;

public class Main {
  private static CentralParser parser;
  private static CentralChecker checker;
  private static List<GameObject> parsed;

  public static void main(String[] args) {

    try {
      parser = new CentralParser(args[0]);
      parsed = parser.parseParameters();

      checker = new CentralChecker(parsed);
      checker.checkParameters();

      System.out.println(parsed.get(0));
    } catch (ParserException | CheckerException e) {
      e.printStackTrace();
    }
  }
}
