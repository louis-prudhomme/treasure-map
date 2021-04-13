package fr.carbonit.model.parameters;

import fr.carbonit.model.Axes;
import fr.carbonit.model.ParameterHeadersEnum;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Treasure extends GameObject {
  private final int worth;

  public Treasure(@NonNull Axes position, int worth) {
    super(position, ParameterHeadersEnum.TREASURE);
    this.worth = worth;
  }

  @Override
  public String toString() {
    return String.format("T(%d)", worth);
  }
}
