package fr.carbonit.model.parameters;

import fr.carbonit.model.Axes;
import lombok.Data;

@Data
public class Mountain implements GameObject {
  private final Axes position;
}
