package fr.carbonit.model.actions;

import fr.carbonit.model.Game;
import fr.carbonit.model.objects.Adventurer;
import lombok.NonNull;

public abstract class AbstractAction {
  // todo change game state
  public abstract void applyTo(@NonNull Game game, @NonNull Adventurer adventurer);
}
