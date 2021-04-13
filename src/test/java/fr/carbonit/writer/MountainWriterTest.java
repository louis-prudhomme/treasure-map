package fr.carbonit.writer;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.Mountain;
import org.junit.jupiter.api.Test;

public class MountainWriterTest {
  @Test
  public void writesCorrectlyTest() {
    assert (new MountainWriter().write(new Mountain(new Axes(1, 2))).equals("M - 1 - 2"));
  }
}
