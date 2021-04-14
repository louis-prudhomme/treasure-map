package fr.carbonit.utils;

import lombok.NonNull;

import java.util.function.Consumer;

@FunctionalInterface
public interface CheckedConsumer<T> {
  static <T> @NonNull Consumer<T> wrap(@NonNull CheckedConsumer<T> checkedConsumer) {
    return t -> {
      try {
        checkedConsumer.apply(t);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  void apply(T t) throws Exception;
}
