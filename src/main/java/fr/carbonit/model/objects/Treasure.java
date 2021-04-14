package fr.carbonit.model.objects;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.exception.TreasureOutOfBoundsException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Treasure extends GameObject {
  private int worth;

  public Treasure(@NonNull Axes position, int worth) {
    super(GameObjectHeadersEnum.TREASURE, position);
    this.worth = worth;
  }

  public void wasTaken() {
    this.worth--;
    if (worth < 0) throw new TreasureOutOfBoundsException(this);
  }

  @Override
  public String toString() {
    return String.format("T(%d)", worth);
  }
}
