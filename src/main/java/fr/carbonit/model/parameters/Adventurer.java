package fr.carbonit.model.parameters;

import fr.carbonit.model.Axes;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Adventurer implements GameObject {
  @NonNull private final String name;
  @NonNull private Axes position;
  @NonNull private RotationEnum rotation;
  @NonNull private List<MovementEnum> movements;
  private int numberOfTreasures;
}
