package fr.carbonit.model.actions;

import fr.carbonit.model.Game;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.RotationEnum;
import lombok.NonNull;

public abstract class AbstractRotationAction extends AbstractAction {

  @NonNull private final RotationMode mode;

  protected AbstractRotationAction(@NonNull RotationMode mode) {
    this.mode = mode;
  }

  @Override
  public void applyTo(@NonNull Game game, @NonNull Adventurer adventurer) {
    int newIndex = adventurer.getRotation().ordinal();
    newIndex = newIndex - (mode == RotationMode.LEFT ? 1 : -1);
    newIndex = Math.floorMod(newIndex, RotationEnum.values().length);
    adventurer.setRotation(RotationEnum.values()[newIndex]);
  }
}
