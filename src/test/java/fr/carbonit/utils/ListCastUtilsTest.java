package fr.carbonit.utils;

import fr.carbonit.model.Axes;
import fr.carbonit.model.objects.*;
import lombok.Getter;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class ListCastUtilsTest {
  @Getter
  private static final List<Class<? extends GameObject>> gameObjectClasses =
      List.of(Adventurer.class, Mountain.class, Board.class, Treasure.class);

  private static final List<GameObject> testList =
      List.of(
          new Treasure(mock(Axes.class), 1),
          new Mountain(mock(Axes.class)),
          new Adventurer(mock(Axes.class), "", RotationEnum.NORD, mock(LinkedList.class)),
          new Board(mock(Axes.class)),
          new Treasure(mock(Axes.class), 1),
          new Mountain(mock(Axes.class)),
          new Adventurer(mock(Axes.class), "", RotationEnum.NORD, mock(LinkedList.class)),
          new Board(mock(Axes.class)));

  @Getter
  private static final List<GameObject> listOfFirsts =
      List.of(
          new Treasure(mock(Axes.class), 2),
          new Adventurer(mock(Axes.class), "First", RotationEnum.NORD, mock(LinkedList.class)),
          new Mountain(mock(Axes.class)),
          new Board(mock(Axes.class)));

  private static List<GameObject> listWithFirsts;

  @BeforeEach
  public void init() {
    listWithFirsts = new ArrayList<>();
    listWithFirsts.addAll(listOfFirsts);
    listWithFirsts.addAll(testList);
  }

  @ParameterizedTest
  @MethodSource("getGameObjectClasses")
  public void parameterAggregatorWorksForEachClass(@NonNull Class<? extends GameObject> clazz) {
    var res = ListCastUtils.parameterAggregator(testList, clazz);
    assert (res.size() == 2);
    assert (res.stream().map(Object::getClass).allMatch(clazz::equals));
  }

  @ParameterizedTest
  @MethodSource("getListOfFirsts")
  public void findFirstWorksForEachClass(@NonNull GameObject first) {
    var res = ListCastUtils.findFirstOfType(listWithFirsts, first.getClass());
    assert (res.isPresent());
    assert (res.get().equals(first));
  }
}
