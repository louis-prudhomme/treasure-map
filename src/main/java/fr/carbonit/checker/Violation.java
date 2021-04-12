package fr.carbonit.checker;

import lombok.Data;
import lombok.NonNull;

@Data
public abstract class Violation {
  @NonNull private final Object reason;

  @Override
  public String toString() {
    return String.format("%s w/ reason: %s", this.getClass().getSimpleName(), reason);
  }
}
