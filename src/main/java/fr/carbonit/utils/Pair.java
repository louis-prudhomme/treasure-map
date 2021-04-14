package fr.carbonit.utils;

import lombok.Data;

@Data
public class Pair<A, B> {
  final A a;
  final B b;

  public static <U, V> Pair<U, V> of(U u, V v) {
    return new Pair<>(u, v);
  }
}
