package fr.carbonit.parser;

import fr.carbonit.parser.exception.WrongArgumentFormatException;
import fr.carbonit.parser.exception.WrongArgumentNumberException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdventurerParserTest {
  private static final String validAdventurer = "A - Lara - 1 - 1 - N - ADAD";
  private static final String invalidAdventurerNumberArgsLot = "A - 1 - 1 - 1";
  private static final String invalidAdventurerNumberArgsFew = "A - 1";
  private static final String invalidAdventurerParseArgs = "A - сука блядь - 1";

  private static AdventurerParser parser;

  @BeforeEach
  public void init() {
    parser = new AdventurerParser();
  }

  @SneakyThrows
  @Test
  public void validAdventurerStringIsParsedTest() {
    var Adventurer = parser.parseParameter(validAdventurer);
    assert (Adventurer.getPosition().getX() == 1);
    assert (Adventurer.getPosition().getY() == 1);
  }

  @Test
  public void invalidTooMuchArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class,
        () -> parser.parseParameter(invalidAdventurerNumberArgsLot));
  }

  @Test
  public void invalidTooFewArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class,
        () -> parser.parseParameter(invalidAdventurerNumberArgsFew));
  }

  @Test
  public void invalidIncorrectArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentFormatException.class,
        () -> parser.parseParameter(invalidAdventurerParseArgs));
  }
}
