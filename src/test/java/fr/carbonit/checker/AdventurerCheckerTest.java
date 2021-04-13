package fr.carbonit.checker;

import fr.carbonit.checker.adventurer.AdventurerChecker;
import fr.carbonit.checker.adventurer.AdventurerIllegalPositionViolation;
import fr.carbonit.checker.adventurer.AdventurerNameNotUniqueViolation;
import fr.carbonit.checker.adventurer.AdventurerNotOnBoardViolation;
import fr.carbonit.model.Axes;
import fr.carbonit.model.actions.ActionEnum;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.Board;
import fr.carbonit.model.objects.RotationEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class AdventurerCheckerTest {
  public static List<Adventurer> testList;
  public static AdventurerChecker checker;
  public static Board reference;

  public static Adventurer lara;

  @BeforeEach
  public void init() {
    reference = new Board(new Axes(4, 4));
    checker = new AdventurerChecker(reference);

    lara =
        new Adventurer(
            new Axes(1, 1),
            "Lara",
            RotationEnum.NORD,
            new LinkedList<>(List.of(ActionEnum.DROITE)));
  }

  @Test
  public void goodAdventurerPasses() {
    testList = List.of(lara);

    var res = checker.checkParameters(testList);
    assert (res.size() == 0);
  }

  @Test
  public void adventurerIllegalPositionTest() {
    lara.setPosition(new Axes(-1, -1));
    testList = List.of(lara);

    var res = checker.checkParameters(testList);
    assert (res.size() == 1);
    assert (res.get(0).getClass() == AdventurerIllegalPositionViolation.class);
  }

  @Test
  public void adventurerNotOnBoardTest() {
    lara.setPosition(new Axes(100, 1));
    testList = List.of(lara);

    var res = checker.checkParameters(testList);
    assert (res.size() == 1);
    assert (res.get(0).getClass() == AdventurerNotOnBoardViolation.class);
  }

  @Test
  public void adventurersWithSameNameTest() {
    testList = List.of(lara, lara);

    var res = checker.checkParameters(testList);
    assert (res.size() == 1);
    assert (res.get(0).getClass() == AdventurerNameNotUniqueViolation.class);
  }
}
