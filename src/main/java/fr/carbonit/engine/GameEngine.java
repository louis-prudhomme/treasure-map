package fr.carbonit.engine;

import fr.carbonit.exception.ShouldNotHappenException;
import fr.carbonit.model.actions.EnumActionMapper;
import fr.carbonit.model.objects.Adventurer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GameEngine {
  @NonNull private final Game game;

  public void playGame() {
    var liveAdventurers = getLiveAdventurers();
    while (!liveAdventurers.isEmpty()) {
      for (Adventurer a : liveAdventurers) {
        var nextAction = a.getMovements().poll();
        EnumActionMapper.mapToAction(
                Optional.ofNullable(nextAction).orElseThrow(ShouldNotHappenException::new))
            .applyTo(game, a);
      }
      liveAdventurers = getLiveAdventurers();
    }
  }

  private List<Adventurer> getLiveAdventurers() {
    return game.getAdventurerRegistry().stream()
        .filter(adventurer -> !adventurer.getMovements().isEmpty())
        .collect(Collectors.toList());
  }
}
