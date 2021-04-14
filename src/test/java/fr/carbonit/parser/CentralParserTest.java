package fr.carbonit.parser;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.*;
import fr.carbonit.utils.Pair;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static fr.carbonit.model.actions.ActionEnum.DROITE;
import static fr.carbonit.model.objects.RotationEnum.NORD;

public class CentralParserTest {
  @Getter
  private static final Set<Pair<String, GameObject>> givenAndExpected =
      Set.of(
          Pair.of("C - 1 - 2", new Board(new Axes(2, 1))),
          Pair.of(
              "A - Lara - 1 - 2 - N - D",
              new Adventurer(new Axes(2, 1), "Lara", NORD, new LinkedList<>(List.of(DROITE)))),
          Pair.of("M - 1 - 2", new Mountain(new Axes(2, 1))),
          Pair.of("T - 1 - 2 - 3", new Treasure(new Axes(2, 1), 3)));

  private CentralParser toTest;
  @Getter private StringReader stringReader;

  public void init(@NonNull String toRead) {
    stringReader = new StringReader(toRead);
    toTest = new CentralParser(this::getStringReader);
  }

  @SneakyThrows
  @ParameterizedTest
  @MethodSource("getGivenAndExpected")
  public void parsesCorrectlyTest(@NonNull Pair<String, GameObject> givenExpected) {
    init(givenExpected.getA());
    var res = toTest.parseParameters();
    assert (res.size() == 1);
    assert (res.get(0).equals(givenExpected.getB()));
  }
}
