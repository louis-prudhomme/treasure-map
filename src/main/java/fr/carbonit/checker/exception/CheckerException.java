package fr.carbonit.checker.exception;

public abstract class CheckerException extends Exception {
  public CheckerException() {
    super();
  }

  public CheckerException(String message) {
    super(message);
  }

  public CheckerException(String message, Throwable cause) {
    super(message, cause);
  }

  public CheckerException(Throwable cause) {
    super(cause);
  }

  protected CheckerException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
