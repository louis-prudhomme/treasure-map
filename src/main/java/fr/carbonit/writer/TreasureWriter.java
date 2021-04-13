package fr.carbonit.writer;

import fr.carbonit.model.objects.Treasure;
import lombok.NonNull;

public class TreasureWriter extends AbstractGameObjectWriter<Treasure> {
  public TreasureWriter() {
    super(Treasure.class);
  }

  @Override
  protected @NonNull String write(@NonNull Treasure toWrite) {
    return String.join(SEPARATOR, super.write(toWrite), String.valueOf(toWrite.getWorth()));
  }
}
