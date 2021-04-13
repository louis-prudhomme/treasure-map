package fr.carbonit.model.parameters;

import fr.carbonit.model.Axes;
import fr.carbonit.model.ParameterHeadersEnum;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Queue;

@Getter
public class Adventurer extends GameObject {
  @NonNull private final String name;
  @NonNull private RotationEnum rotation;
  @NonNull private Queue<MovementEnum> movements;
  @Setter private int numberOfTreasures;

  public Adventurer(
      @NonNull Axes position,
      @NonNull String name,
      @NonNull RotationEnum rotation,
      @NonNull Queue<MovementEnum> movements) {
    super(position, ParameterHeadersEnum.ADVENTURER);
    this.name = name;
    this.rotation = rotation;
    this.movements = movements;
    this.numberOfTreasures = 0;
  }

  @Override
  public String toString() {
    return String.format("%s(%s)", super.toString(), name);
  }
}
