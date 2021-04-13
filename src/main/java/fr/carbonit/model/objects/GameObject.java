package fr.carbonit.model.objects;

import fr.carbonit.model.Axes;
import lombok.*;

@Getter
@AllArgsConstructor
public abstract class GameObject {
  @NonNull private final GameObjectHeadersEnum header;

  @NonNull
  @Setter(AccessLevel.PROTECTED)
  protected Axes position;

  public String toString() {
    return String.valueOf(header.getUnderlying());
  }
}
