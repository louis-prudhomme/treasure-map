package utils;

import fr.carbonit.model.parameters.GameObject;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class ListCastUtils {

  public static @NonNull <T extends GameObject> List<T> parameterAggregator(
      @NonNull List<GameObject> toAggregate, @NonNull Class<T> clazz) {
    return toAggregate.stream()
        .filter(gameObject -> gameObject.getClass().equals(clazz))
        .map(clazz::cast)
        .collect(Collectors.toList());
  }
}
