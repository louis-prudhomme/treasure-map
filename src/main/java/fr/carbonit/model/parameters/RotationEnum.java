package fr.carbonit.model.parameters;

import fr.carbonit.exception.ShouldNotHappenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum RotationEnum {
  NORD('N'),
  SUD('S'),
  EST('E'),
  OUEST('O');

  private final char underlying;

  public static RotationEnum get(char c) {
    return Arrays.stream(values())
        .filter(v -> v.underlying == c)
        .findFirst()
        .orElseThrow(ShouldNotHappenException::new);
  }

  public static boolean isCharKeyOf(char c) {
    return Arrays.stream(values())
        .map(RotationEnum::getUnderlying)
        .anyMatch(character -> character == c);
  }
}
