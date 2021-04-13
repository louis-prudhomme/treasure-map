package fr.carbonit.writer;

import fr.carbonit.model.objects.GameObject;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public abstract class AbstractGameObjectWriter<T extends GameObject> {
  @NonNull protected static final String SEPARATOR = " - ";
  @NonNull private final Class<T> target;

  protected @NonNull <U extends GameObject> String writeUncast(U toWrite) {
    return write(target.cast(toWrite));
  }

  protected @NonNull String write(T toWrite) {
    return String.join(
        SEPARATOR,
        String.valueOf(toWrite.getHeader().getUnderlying()),
        String.valueOf(toWrite.getPosition().getX()),
        String.valueOf(toWrite.getPosition().getY()));
  }
}
