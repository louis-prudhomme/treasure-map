package fr.carbonit.parser;

import fr.carbonit.model.objects.GameObject;
import fr.carbonit.parser.exception.ParserException;
import fr.carbonit.parser.exception.WrongArgumentNumberException;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public abstract class AbstractParameterParser<T extends GameObject> {
  protected static final String SEPARATOR = " - ";

  private final int expectedNumberOfParameters;

  public abstract @NonNull T parseParameter(@NonNull String line) throws ParserException;

  protected @NonNull String[] trySplit(@NonNull String parameter) throws ParserException {
    var args = parameter.split(SEPARATOR);

    if (args.length != expectedNumberOfParameters)
      throw new WrongArgumentNumberException(expectedNumberOfParameters, args.length);

    return args;
  }
}
