package fr.carbonit.writer;

import fr.carbonit.model.objects.Adventurer;
import lombok.NonNull;

public class AdventurerWriter extends AbstractGameObjectWriter<Adventurer> {
  public AdventurerWriter() {
    super(Adventurer.class);
  }

  @Override
  protected @NonNull String write(@NonNull Adventurer toWrite) {
    return String.join(
        SEPARATOR,
        String.valueOf(toWrite.getHeader().getUnderlying()),
        toWrite.getName(),
        String.valueOf(toWrite.getPosition().getX()),
        String.valueOf(toWrite.getPosition().getY()),
        String.valueOf(toWrite.getRotation().getUnderlying()),
        String.valueOf(toWrite.getNumberOfTreasures()));
  }
}
