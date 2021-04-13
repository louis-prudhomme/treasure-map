package fr.carbonit.model.objects;

import fr.carbonit.exception.ShouldNotHappenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum GameObjectHeadersEnum {
  BOARD('C'),
  TREASURE('T'),
  MOUNTAIN('M'),
  ADVENTURER('A');

  @Getter private final char underlying;

  public static GameObjectHeadersEnum get(char c) {
    return Arrays.stream(GameObjectHeadersEnum.values())
        .filter(v -> v.underlying == Character.toUpperCase(c))
        .findFirst()
        .orElseThrow(ShouldNotHappenException::new);
  }

  public static boolean isCharKeyOf(char c) {
    return Arrays.stream(GameObjectHeadersEnum.values())
        .map(e -> e.underlying)
        .anyMatch(character -> character == Character.toUpperCase(c));
  }
}
