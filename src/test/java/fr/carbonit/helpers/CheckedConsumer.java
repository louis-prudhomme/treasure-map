package fr.carbonit.helpers;

import java.util.function.Consumer;

@FunctionalInterface
public interface CheckedConsumer<T> {
  static <T> Consumer<T> wrap(CheckedConsumer<T> checkedConsumer) {
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
