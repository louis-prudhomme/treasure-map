package fr.carbonit.parser;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Treasure;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.parser.exception.WrongArgumentFormatException;
import lombok.NonNull;

public class TreasureParser extends AbstractGameObjectParser<Treasure> {
  private static final int EXPECTED_NUMBER_ARGUMENTS = 4;

  public TreasureParser() {
    super(EXPECTED_NUMBER_ARGUMENTS);
  }

  @Override
  public @NonNull Treasure parseParameter(@NonNull String line) throws ParserException {
    var args = super.trySplit(line);

    try {
      return new Treasure(
          new Axes(Integer.parseInt(args[2]), Integer.parseInt(args[1])),
          Integer.parseInt(args[3]));
    } catch (NumberFormatException e) {
      throw new WrongArgumentFormatException(e);
    }
  }
}
