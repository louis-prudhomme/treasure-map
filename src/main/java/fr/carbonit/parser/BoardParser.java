package fr.carbonit.parser;

import fr.carbonit.model.Axes;
import fr.carbonit.model.parameters.Board;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.parser.exception.WrongArgumentFormatException;
import lombok.NonNull;

public class BoardParser extends AbstractParameterParser<Board> {
  private static final int NUMBER_OF_EXPECTED_ARGUMENTS = 3;

  public BoardParser() {
    super(NUMBER_OF_EXPECTED_ARGUMENTS);
  }

  @Override
  public @NonNull Board parseParameter(@NonNull String line) throws ParserException {
    var args = super.trySplit(line);

    try {
      return new Board(new Axes(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
    } catch (NumberFormatException e) {
      throw new WrongArgumentFormatException(e);
    }
  }
}
