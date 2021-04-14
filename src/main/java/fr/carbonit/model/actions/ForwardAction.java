package fr.carbonit.model.actions;

import fr.carbonit.engine.Game;
import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.GameObject;
import fr.carbonit.model.objects.GameObjectHeadersEnum;
import lombok.NonNull;

public class ForwardAction extends AbstractAction {
  @Override
  public void applyTo(@NonNull Game game, @NonNull Adventurer adventurer) {
      var newPosition = computeNewPosition(game, adventurer);
      if (canMoveIn(game, newPosition))
          moveAdventurer(game, adventurer, newPosition);
  }

  public @NonNull Axes computeNewPosition(@NonNull Game game, @NonNull Adventurer adventurer) {
    return switch (adventurer.getRotation()) {
          case NORD ->
            adventurer.getPosition().moveY(-1);
          case EST ->
            adventurer.getPosition().moveX(1);
          case SUD ->
            adventurer.getPosition().moveY(1);
          case OUEST ->
            adventurer.getPosition().moveX(-1);
        };
  }

  public boolean canMoveIn(@NonNull Game game, @NonNull Axes position) {
    return game.getDimension().isPositionWithin(position) && game.get(position)
            .map(GameObject::getHeader)
            .map(header -> header == GameObjectHeadersEnum.TREASURE)
            .orElse(true);
  }

  private void moveAdventurer(@NonNull Game game, @NonNull Adventurer adventurer,@NonNull Axes newPosition) {
      game.remove(adventurer);
      adventurer.setPosition(newPosition);
      game.add(adventurer);
  }
}
