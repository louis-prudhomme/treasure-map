package fr.carbonit.model.objects;

import fr.carbonit.model.Axes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public abstract class GameObject {
  @NonNull private final ParameterHeadersEnum header;
  @NonNull protected Axes position;

  public String toString() {
    return String.valueOf(header.getUnderlying());
  }
}
