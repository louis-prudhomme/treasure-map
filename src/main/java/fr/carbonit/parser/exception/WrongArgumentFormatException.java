package fr.carbonit.parser.exception;

public class WrongArgumentFormatException extends ParserException {
  public WrongArgumentFormatException(Throwable cause) {
    super(cause);
  }

  public WrongArgumentFormatException(String message) {
    super(message);
  }
}
