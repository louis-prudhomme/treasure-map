package fr.carbonit.parser;

import fr.carbonit.parser.exception.WrongArgumentFormatException;
import fr.carbonit.parser.exception.WrongArgumentNumberException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreasureParserTest {
  private static final String validTreasure = "T - 1 - 1 - 1";
  private static final String invalidTreasureNumberArgsLot = "T - 1 - 1 - 1 - 1";
  private static final String invalidTreasureNumberArgsFew = "T - 1 - 1";
  private static final String invalidTreasureParseArgs = "T - сука блядь - 1 - 1";

  private static TreasureParser parser;

  @BeforeEach
  public void init() {
    parser = new TreasureParser();
  }

  @SneakyThrows
  @Test
  public void validTreasureStringIsParsedTest() {
    var Treasure = parser.parseParameter(validTreasure);
    assert (Treasure.getPosition().getX() == 1);
    assert (Treasure.getPosition().getY() == 1);
  }

  @Test
  public void invalidTooMuchArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class,
        () -> parser.parseParameter(invalidTreasureNumberArgsLot));
  }

  @Test
  public void invalidTooFewArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class,
        () -> parser.parseParameter(invalidTreasureNumberArgsFew));
  }

  @Test
  public void invalidIncorrectArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentFormatException.class, () -> parser.parseParameter(invalidTreasureParseArgs));
  }
}
