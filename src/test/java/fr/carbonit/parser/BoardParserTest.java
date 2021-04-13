package fr.carbonit.parser;

import fr.carbonit.parser.exception.WrongArgumentFormatException;
import fr.carbonit.parser.exception.WrongArgumentNumberException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardParserTest {
  private static final String validBoard = "B - 1 - 1";
  private static final String invalidBoardNumberArgsLot = "B - 1 - 1 - 1";
  private static final String invalidBoardNumberArgsFew = "B - 1";
  private static final String invalidBoardParseArgs = "B - сука блядь - 1";

  private static BoardParser parser;

  @BeforeEach
  public void init() {
    parser = new BoardParser();
  }

  @SneakyThrows
  @Test
  public void validBoardStringIsParsedTest() {
    var board = parser.parseParameter(validBoard);
    assert (board.getPosition().getX() == 1);
    assert (board.getPosition().getY() == 1);
  }

  @Test
  public void invalidTooMuchArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class, () -> parser.parseParameter(invalidBoardNumberArgsLot));
  }

  @Test
  public void invalidTooFewArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class, () -> parser.parseParameter(invalidBoardNumberArgsFew));
  }

  @Test
  public void invalidIncorrectArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentFormatException.class, () -> parser.parseParameter(invalidBoardParseArgs));
  }
}
