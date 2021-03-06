package fr.carbonit.parser.exception;

public abstract class ParserException extends Exception {
  public ParserException() {
    super();
  }

  public ParserException(String message) {
    super(message);
  }

  public ParserException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParserException(Throwable cause) {
    super(cause);
  }

  protected ParserException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
