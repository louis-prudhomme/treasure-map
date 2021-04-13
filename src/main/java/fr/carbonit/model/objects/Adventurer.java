package fr.carbonit.model.objects;

import fr.carbonit.model.Axes;
import fr.carbonit.model.ParameterHeadersEnum;
import fr.carbonit.model.actions.ActionEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Queue;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Adventurer extends GameObject {
  @NonNull @EqualsAndHashCode.Include
  private final String name;
  @NonNull @Setter private RotationEnum rotation;
  @NonNull private Queue<ActionEnum> movements;
  private int numberOfTreasures;

  public Adventurer(
      @NonNull Axes position,
      @NonNull String name,
      @NonNull RotationEnum rotation,
      @NonNull Queue<ActionEnum> movements) {
    super(ParameterHeadersEnum.ADVENTURER, position);
    this.name = name;
    this.rotation = rotation;
    this.movements = movements;
    this.numberOfTreasures = 0;
  }

  public void setPosition(@NonNull Axes position) {
    this.position = position;
  }

  public void pickupTreasure() {
    numberOfTreasures++;
  }

  @Override
  public String toString() {
    return String.format("%s(%s)", getRotationString(), name);
  }

  private String getRotationString() {
    return switch (rotation) {
      case NORD -> "↑";
      case EST -> "→";
      case SUD -> "↓";
      case OUEST -> "←";
    } + getRotation().getUnderlying();
  }
}
