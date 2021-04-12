package fr.carbonit.model.parameters;

import fr.carbonit.model.Axes;
import lombok.Data;
import lombok.NonNull;

@Data
public class Treasure implements GameObject {
  @NonNull private final Axes position;
  private final int worth;
}
