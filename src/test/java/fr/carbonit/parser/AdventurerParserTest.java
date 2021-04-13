package fr.carbonit.parser;

import fr.carbonit.helpers.CheckedConsumer;
import fr.carbonit.model.objects.RotationEnum;
import fr.carbonit.parser.exception.WrongArgumentFormatException;
import fr.carbonit.parser.exception.WrongArgumentNumberException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdventurerParserTest {
  private static final String validAdventurer = "A - Lara - 1 - 1 - N - ADAD";
  private static final String invalidAdventurerNumberArgsLot = "A - 1 - 1 - 1";
  private static final String invalidAdventurerNumberArgsFew = "A - 1";
  private static final String invalidAdventurerParseArgs = "A - Lara - сука блядь - 1 - N - ADAD";

  private static final List<String> goodRotations =
      Arrays.stream(RotationEnum.values())
          .map(RotationEnum::getUnderlying)
          .map(String::valueOf)
          .collect(Collectors.toList());

  private static final List<String> badRotations =
      "abcdefghijklmnopqrstuvwxyz"
          .chars()
          .mapToObj(c -> (char) c)
          .map(String::valueOf)
          .filter(o -> !goodRotations.contains(o))
          .collect(Collectors.toList());

  private static AdventurerParser parser;

  @BeforeEach
  public void init() {
    parser = new AdventurerParser();
  }

  @SneakyThrows
  @Test
  public void validAdventurerStringIsParsedTest() {
    var Adventurer = parser.parseParameter(validAdventurer);
    assert (Adventurer.getPosition().getX() == 1);
    assert (Adventurer.getPosition().getY() == 1);
  }

  @Test
  public void invalidTooMuchArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class,
        () -> parser.parseParameter(invalidAdventurerNumberArgsLot));
  }

  @Test
  public void invalidTooFewArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentNumberException.class,
        () -> parser.parseParameter(invalidAdventurerNumberArgsFew));
  }

  @Test
  public void invalidIncorrectArgsThrowsTest() {
    Assertions.assertThrows(
        WrongArgumentFormatException.class,
        () -> parser.parseParameter(invalidAdventurerParseArgs));
  }

  @SneakyThrows
  @Test
  public void validRotationsPassTest() {
    goodRotations.forEach(CheckedConsumer.wrap(parser::isRotationParsableOrThrow));
  }

  @Test
  public void invalidRotationsThrowTest() {
    List<Exception> res = new ArrayList<>();
    for (String rotation : badRotations) {
      try {
        parser.isRotationParsableOrThrow(rotation);
      } catch (WrongArgumentFormatException e) {
        res.add(e);
      }
    }
    assert (res.size() == badRotations.size());
  }
}
