package fr.carbonit.engine;

import fr.carbonit.model.actions.EnumActionMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameEngine {
  @NonNull private final Game game;

  public void playGame() {
    game.getAdventurerRegistry()
        .forEach(
            adventurer ->
                adventurer.getMovements().stream()
                    .map(EnumActionMapper::mapToAction)
                    .forEach(abstractAction -> abstractAction.applyTo(game, adventurer)));
  }
}
