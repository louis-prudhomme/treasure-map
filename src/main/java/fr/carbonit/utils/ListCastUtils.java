package fr.carbonit.utils;

import fr.carbonit.model.objects.GameObject;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListCastUtils {

  public static @NonNull <T extends GameObject> List<T> parameterAggregator(
      @NonNull List<GameObject> toAggregate, @NonNull Class<T> clazz) {
    return toAggregate.stream()
        .filter(gameObject -> gameObject.getClass().equals(clazz))
        .map(clazz::cast)
        .collect(Collectors.toList());
  }

  public static <T extends GameObject> Optional<T> findFirstOfType(
      @NonNull List<? extends GameObject> haystack, @NonNull Class<T> clazz) {
    return haystack.stream()
        .filter(haystick -> haystick.getClass().equals(clazz))
        .findFirst()
        .map(clazz::cast);
  }
}
