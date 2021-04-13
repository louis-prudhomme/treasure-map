package fr.carbonit.model;

import fr.carbonit.exception.ShouldNotHappenException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ParameterHeadersEnum {
  BOARD('C'),
  TREASURE('T'),
  MOUNTAIN('M'),
  ADVENTURER('A');

  private final char underlying;

  public static ParameterHeadersEnum get(char c) {
    return Arrays.stream(values())
        .filter(v -> v.underlying == Character.toUpperCase(c))
        .findFirst()
        .orElseThrow(ShouldNotHappenException::new);
  }

  public static boolean isCharKeyOf(char c) {
    return Arrays.stream(values())
        .map(e -> e.underlying)
        .anyMatch(character -> character == Character.toUpperCase(c));
  }

  @Override
  public String toString() {
    return String.valueOf(underlying);
  }
}
