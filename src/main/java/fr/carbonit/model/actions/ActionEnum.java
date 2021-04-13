package fr.carbonit.model.actions;

import fr.carbonit.exception.ShouldNotHappenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ActionEnum {
  AVANCER('A'),
  DROITE('D'),
  GAUCHE('G');

  private final char underlying;

  public static ActionEnum get(char c) {
    return Arrays.stream(values())
        .filter(v -> v.underlying == c)
        .findFirst()
        .orElseThrow(ShouldNotHappenException::new);
  }

  public static boolean isCharKeyOf(char c) {
    return Arrays.stream(values())
        .map(ActionEnum::getUnderlying)
        .anyMatch(character -> character == c);
  }
}
