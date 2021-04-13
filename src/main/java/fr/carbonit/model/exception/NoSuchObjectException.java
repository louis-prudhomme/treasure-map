package fr.carbonit.model.exception;

import fr.carbonit.model.objects.GameObject;
import lombok.NonNull;

public class NoSuchObjectException extends RuntimeException {
  private static final String TEXT = "Game object %s is not %s";

  public NoSuchObjectException(@NonNull GameObject a, @NonNull Object b) {
    super(String.format(TEXT, a, b));
  }
}
