package fr.carbonit.writer;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Board;
import org.junit.jupiter.api.Test;

public class BoardWriterTest {
  @Test
  public void writesCorrectlyTest() {
    assert (new BoardWriter().write(new Board(new Axes(1, 2))).equals("C - 1 - 2"));
  }
}
