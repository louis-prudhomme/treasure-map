package fr.carbonit.checker;

import fr.carbonit.checker.treasure.TreasureChecker;
import fr.carbonit.checker.treasure.TreasureIllegalPositionViolation;
import fr.carbonit.checker.treasure.TreasureIllegalValueViolation;
import fr.carbonit.checker.treasure.TreasureNotOnBoardViolation;
import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TreasureCheckerTest {
  public static List<Treasure> testList;
  public static TreasureChecker checker;
  public static Board reference;

  @BeforeEach
  public void init() {
    reference = new Board(new Axes(4, 4));
    checker = new TreasureChecker(reference);
  }

  @Test
  public void validTreasurePassesTest() {
    testList = List.of(new Treasure(new Axes(2, 2), 2));

    var res = checker.checkParameters(testList);
    assert (res.size() == 0);
  }

  @Test
  public void treasureIllegalPositionTest() {
    testList = List.of(new Treasure(new Axes(-2, 2), 2));

    var res = checker.checkParameters(testList);
    assert (res.size() == 2);
    assert (res.stream()
        .map(Object::getClass)
        .anyMatch(violation -> violation == TreasureIllegalPositionViolation.class));
  }

  @Test
  public void treasureNotOnBoardTest() {
    testList = List.of(new Treasure(new Axes(200, 2), 2));

    var res = checker.checkParameters(testList);
    assert (res.size() == 1);
    assert (res.get(0).getClass() == TreasureNotOnBoardViolation.class);
  }

  @Test
  public void treasureIllegalValueTest() {
    testList = List.of(new Treasure(new Axes(2, 2), -2));

    var res = checker.checkParameters(testList);
    assert (res.size() == 1);
    assert (res.get(0).getClass() == TreasureIllegalValueViolation.class);
  }
}
