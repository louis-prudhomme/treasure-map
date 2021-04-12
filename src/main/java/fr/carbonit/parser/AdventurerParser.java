package fr.carbonit.parser;

import fr.carbonit.model.Axes;
import fr.carbonit.model.parameters.Adventurer;
import fr.carbonit.model.parameters.MovementEnum;
import fr.carbonit.model.parameters.RotationEnum;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.parser.exception.WrongArgumentFormatException;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class AdventurerParser extends AbstractParameterParser<Adventurer> {
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
          args[1],
          new Axes(Integer.parseInt(args[2]), Integer.parseInt(args[3])),
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

  public @NonNull List<MovementEnum> tryParseMovements(@NonNull String movementsSentence)
      throws WrongArgumentFormatException {
    if (!movementsSentence.chars().mapToObj(c -> (char) c).map(Character::toUpperCase).allMatch(MovementEnum::isCharKeyOf))
      throw new WrongArgumentFormatException(movementsSentence);

    return movementsSentence.chars().mapToObj(c -> (char) c).map(Character::toUpperCase).map(MovementEnum::get).collect(Collectors.toList());
  }
}
