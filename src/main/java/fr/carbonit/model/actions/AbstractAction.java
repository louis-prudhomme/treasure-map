package fr.carbonit.model.actions;

import fr.carbonit.engine.Game;
import fr.carbonit.model.objects.Adventurer;
import lombok.NonNull;

public abstract class AbstractAction {
  // todo change game state
  public abstract void applyTo(@NonNull Game game, @NonNull Adventurer adventurer);
}
