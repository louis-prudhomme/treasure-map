package fr.carbonit.writer;

import fr.carbonit.model.Axes;
import fr.carbonit.model.actions.ActionEnum;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.RotationEnum;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class AdventurerWriterTest {
  @Test
  public void writesCorrectlyTest() {
    assert (new AdventurerWriter()
        .write(
            new Adventurer(
                new Axes(1, 2),
                "Lara",
                RotationEnum.NORD,
                new LinkedList<>(List.of(ActionEnum.DROITE))))
        .equals("A - Lara - 1 - 2 - N - 0"));
  }
}
