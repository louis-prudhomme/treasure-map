package fr.carbonit.model.objects;

import fr.carbonit.model.Axes;
import fr.carbonit.model.ParameterHeadersEnum;
import lombok.NonNull;

public class Mountain extends GameObject {
  public Mountain(@NonNull Axes position) {
    super(ParameterHeadersEnum.MOUNTAIN, position);
  }
}
