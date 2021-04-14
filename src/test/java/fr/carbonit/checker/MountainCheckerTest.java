package fr.carbonit.checker;

import fr.carbonit.checker.mountain.MountainChecker;
import fr.carbonit.checker.mountain.MountainIllegalPositionViolation;
import fr.carbonit.checker.mountain.MountainNotOnBoardViolation;
import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.Mountain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MountainCheckerTest {
  public static List<Mountain> testList;
  public static MountainChecker checker;
  public static Board reference;

  @BeforeEach
  public void init() {
    reference = new Board(new Axes(4, 4));
    checker = new MountainChecker(reference);
  }

  @Test
  public void mountainOnBoardPasses() {
    testList = List.of(new Mountain(new Axes(1, 1)));

    var res = checker.checkParameters(testList);
    assert (res.size() == 0);
  }

  @Test
  public void illegalPositionTest() {
    testList = List.of(new Mountain(new Axes(-1, -1)));

    var res = checker.checkParameters(testList);
    assert (res.size() == 2);
    assert (res.stream()
        .map(Object::getClass)
        .anyMatch(violation -> violation == MountainIllegalPositionViolation.class));
  }

  @Test
  public void mountainNotOnBoardTest() {
    testList = List.of(new Mountain(new Axes(40, 40)));

    var res = checker.checkParameters(testList);
    assert (res.size() == 1);
    assert (res.get(0).getClass() == MountainNotOnBoardViolation.class);
  }
}
