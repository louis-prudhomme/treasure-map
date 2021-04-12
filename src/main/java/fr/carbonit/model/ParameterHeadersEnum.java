package fr.carbonit.model;

import fr.carbonit.exception.ShouldNotHappenException;
import fr.carbonit.model.parameters.MovementEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum ParameterHeadersEnum {
  BOARD('C'),
  TREASURE('T'),
  MOUNTAIN('M'),
  ADVENTURER('A');

  @Getter private final char underlying;

  public static ParameterHeadersEnum get(char c) {
    return Arrays.stream(values())
        .filter(v -> v.underlying == Character.toUpperCase(c))
        .findFirst()
        .orElseThrow(ShouldNotHappenException::new);
  }

  public static boolean isCharKeyOf(char c) {
    return Arrays.stream(values())
            .map(ParameterHeadersEnum::getUnderlying)
            .anyMatch(character -> character == Character.toUpperCase(c));
  }
}
