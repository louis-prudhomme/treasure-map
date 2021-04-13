package fr.carbonit.model.objects.exception;

import fr.carbonit.model.objects.Treasure;
import lombok.NonNull;

public class TreasureOutOfBoundsException extends RuntimeException {
  public TreasureOutOfBoundsException(@NonNull Treasure t) {
    super(t.toString());
  }
}
