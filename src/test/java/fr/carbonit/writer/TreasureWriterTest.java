package fr.carbonit.writer;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Treasure;
import org.junit.jupiter.api.Test;

public class TreasureWriterTest {
  @Test
  public void writesCorrectlyTest() {
    assert (new TreasureWriter().write(new Treasure(new Axes(1, 2), 3)).equals("T - 1 - 2 - 3"));
  }
}
