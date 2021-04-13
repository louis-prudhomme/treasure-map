package fr.carbonit.parser;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Mountain;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.parser.exception.WrongArgumentFormatException;
import lombok.NonNull;

public class MountainParser extends AbstractParameterParser<Mountain> {
  private static final int EXPECTED_NUMBER_ARGUMENTS = 3;

  public MountainParser() {
    super(EXPECTED_NUMBER_ARGUMENTS);
  }

  @Override
  public @NonNull Mountain parseParameter(@NonNull String line) throws ParserException {
    var args = super.trySplit(line);

    try {
      return new Mountain(new Axes(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
    } catch (NumberFormatException e) {
      throw new WrongArgumentFormatException(e);
    }
  }
}
