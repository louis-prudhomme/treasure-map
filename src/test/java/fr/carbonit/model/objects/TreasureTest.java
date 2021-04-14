package fr.carbonit.model.objects;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.exception.TreasureOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class TreasureTest {
  private Treasure toTest = new Treasure(mock(Axes.class), 0);

  @Test
  public void treasureWithNegativeWorthThrowsTest() {
    assertThrows(TreasureOutOfBoundsException.class, () -> toTest.wasTaken());
  }
}
