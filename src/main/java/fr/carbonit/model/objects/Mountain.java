package fr.carbonit.model.objects;

import fr.carbonit.model.Axes;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
public class Mountain extends GameObject {
  public Mountain(@NonNull Axes position) {
    super(GameObjectHeadersEnum.MOUNTAIN, position);
  }
}
