package fr.carbonit.parser;

import fr.carbonit.parser.exception.WrongArgumentFormatException;
import fr.carbonit.parser.exception.WrongArgumentNumberException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MountainParserTest {
  private static final String validMountain = "M - 1 - 1";
  private static final String invalidMountainNumberArgsLot = "M - 1 - 1 - 1";
  private static final String invalidMountainNumberArgsFew = "M - 1";
  private static final String invalidMountainParseArgs = "M - сука блядь - 1";

  private static MountainParser parser;

  @BeforeEach
  public void init() {
    parser = new MountainParser();
  }

  @SneakyThrows
  @Test
  public void validMountainStringIsParsedTest() {
    var Mountain = parser.parseParameter(validMountain);
    assert (Mountain.getPosition().getX() == 1);
    assert (Mountain.getPosition().getY() == 1);
  }

  @Test
  public void invalidTooMuchArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class,
        () -> parser.parseParameter(invalidMountainNumberArgsLot));
  }

  @Test
  public void invalidTooFewArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class,
        () -> parser.parseParameter(invalidMountainNumberArgsFew));
  }

  @Test
  public void invalidIncorrectArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentFormatException.class, () -> parser.parseParameter(invalidMountainParseArgs));
  }
}
