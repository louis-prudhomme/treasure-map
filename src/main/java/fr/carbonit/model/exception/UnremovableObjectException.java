package fr.carbonit.model.exception;

import fr.carbonit.model.objects.GameObject;
import lombok.NonNull;

public class UnremovableObjectException extends RuntimeException {
  private static final String TEXT = "Game object %s cannot be removed: type %s";

  public UnremovableObjectException(@NonNull GameObject a) {
    super(String.format(TEXT, a, a.getHeader().name()));
  }
}
