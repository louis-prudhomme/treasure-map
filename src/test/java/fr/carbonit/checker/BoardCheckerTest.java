package fr.carbonit.checker;

import fr.carbonit.checker.board.BoardChecker;
import fr.carbonit.checker.board.BoardIllegalSizeViolation;
import fr.carbonit.checker.board.BoardNotUniqueViolation;
import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BoardCheckerTest {
  public static List<Board> testList;
  public static BoardChecker checker;

  @BeforeEach
  public void init() {
    checker = new BoardChecker();
  }

  @Test
  public void regularlySizedBoardPassesTest() {
    testList = List.of(new Board(new Axes(1, 1)));

    var res = checker.checkParameters(testList);
    assert (res.size() == 0);
  }

  @Test
  public void boardIllegalBoardSizeTest() {
    testList = List.of(new Board(new Axes(-1, -1)));

    var res = checker.checkParameters(testList);
    assert (res.size() == 1);
    assert (res.get(0).getClass() == BoardIllegalSizeViolation.class);
  }

  @Test
  public void moreThanOneBoardTest() {
    testList = List.of(new Board(new Axes(4, 4)), new Board(new Axes(4, 4)));

    var res = checker.checkParameters(testList);
    assert (res.size() == 1);
    assert (res.get(0).getClass() == BoardNotUniqueViolation.class);
  }
}
