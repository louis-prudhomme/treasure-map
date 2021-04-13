package fr.carbonit.engine;

import fr.carbonit.model.Game;
import fr.carbonit.model.actions.ActionEnum;
import fr.carbonit.model.actions.EnumActionMapper;
import fr.carbonit.model.objects.Adventurer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameEngine {
  @NonNull private final Game game;

  public void playGame() {
    for (Adventurer a : game.getAdventurerRegistry()) {
      for (ActionEnum e : a.getMovements()) {
        var d = EnumActionMapper.mapToAction(e);
        System.out.println(d.getClass().getSimpleName());
        d.applyTo(game, a);
        System.out.println(game);
        System.out.println();
        System.out.println();
      }
    }
  }
}
