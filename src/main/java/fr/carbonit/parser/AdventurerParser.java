package fr.carbonit.parser;

import fr.carbonit.model.Axes;
import fr.carbonit.model.actions.ActionEnum;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.RotationEnum;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.parser.exception.WrongArgumentFormatException;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class AdventurerParser extends AbstractGameObjectParser<Adventurer> {
  private static final int EXPECTED_NUMBER_ARGUMENTS = 6;

  public AdventurerParser() {
    super(EXPECTED_NUMBER_ARGUMENTS);
  }

  @Override
  public @NonNull Adventurer parseParameter(@NonNull String line) throws ParserException {
    var args = super.trySplit(line);

    isRotationParsableOrThrow(args[4]);

    try {
      return new Adventurer(
          new Axes(Integer.parseInt(args[3]), Integer.parseInt(args[2])),
          args[1],
          RotationEnum.get(args[4].charAt(0)),
          tryParseMovements(args[5]));
    } catch (NumberFormatException e) {
      throw new WrongArgumentFormatException(e);
    }
  }

  public void isRotationParsableOrThrow(@NonNull String rotation)
      throws WrongArgumentFormatException {
    if (rotation.length() != 1 || !RotationEnum.isCharKeyOf(rotation.charAt(0)))
      throw new WrongArgumentFormatException(rotation);
  }

  public @NonNull Queue<ActionEnum> tryParseMovements(@NonNull String movementsSentence)
      throws WrongArgumentFormatException {
    if (!movementsSentence.chars().mapToObj(c -> (char) c).allMatch(ActionEnum::isCharKeyOf))
      throw new WrongArgumentFormatException(movementsSentence);

    return movementsSentence
        .chars()
        .mapToObj(c -> (char) c)
        .map(Character::toUpperCase)
        .map(ActionEnum::get)
        .collect(Collectors.toCollection(LinkedList::new));
  }
}
