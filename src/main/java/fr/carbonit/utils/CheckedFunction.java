package fr.carbonit.utils;

import lombok.NonNull;

import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface CheckedFunction<T, R> {
  static <T, R> @NonNull Function<T, R> wrap(@NonNull CheckedFunction<T, R> checkedFunction) {
    return t -> {
      try {
        return checkedFunction.apply(t);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  static <T, R> @NonNull Function<T, R> wrap(
      @NonNull CheckedFunction<T, R> checkedFunction, @NonNull Supplier<RuntimeException> toThrow) {
    return t -> {
      try {
        return checkedFunction.apply(t);
      } catch (Exception e) {
        throw toThrow.get();
      }
    };
  }

  R apply(T t) throws Exception;
}
