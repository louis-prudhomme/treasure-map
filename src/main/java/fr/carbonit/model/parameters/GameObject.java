package fr.carbonit.model.parameters;

import fr.carbonit.model.Axes;
import fr.carbonit.model.ParameterHeadersEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public abstract class GameObject {
  @NonNull protected Axes position;
  @NonNull private ParameterHeadersEnum header;

  public String toString() {
    return header.toString();
  }
}
