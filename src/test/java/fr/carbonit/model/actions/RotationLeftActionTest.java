package fr.carbonit.model.actions;

import fr.carbonit.engine.Game;
import fr.carbonit.model.objects.Adventurer;
import fr.carbonit.model.objects.RotationEnum;
import fr.carbonit.utils.Pair;
import lombok.Getter;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;

import static org.mockito.Mockito.*;

public class RotationLeftActionTest {
  @Getter
  private static final Set<Pair<RotationEnum, RotationEnum>> givenAndExpected =
      Set.of(
          Pair.of(RotationEnum.NORD, RotationEnum.OUEST),
          Pair.of(RotationEnum.OUEST, RotationEnum.SUD),
          Pair.of(RotationEnum.SUD, RotationEnum.EST),
          Pair.of(RotationEnum.EST, RotationEnum.NORD));

  private static final Game mockedGame = mock(Game.class);
  private static final Adventurer mockedAdventurer = mock(Adventurer.class);

  private static RotationLeftAction action;

  @BeforeEach
  public void init() {
    reset(mockedAdventurer, mockedGame);
    action = new RotationLeftAction();
  }

  @ParameterizedTest
  @MethodSource("getGivenAndExpected")
  public void obtainGoodRotationTest(@NonNull Pair<RotationEnum, RotationEnum> givenExpected) {
    when(mockedAdventurer.getRotation()).thenReturn(givenExpected.getA());

    action.applyTo(mockedGame, mockedAdventurer);
    verify(mockedAdventurer, times(1)).setRotation(givenExpected.getB());
  }
}
