package fr.carbonit.parser.exception;

public class WrongArgumentNumberException extends ParserException {
  private static final String TEXT = "Expected %d, received %d";

  public WrongArgumentNumberException(int expected, int received) {
    super(String.format(TEXT, expected, received));
  }
}
