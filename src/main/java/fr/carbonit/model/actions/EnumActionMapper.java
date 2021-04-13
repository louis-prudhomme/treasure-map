package fr.carbonit.model.actions;

import lombok.NonNull;

import java.util.Map;
import java.util.function.Supplier;

public class EnumActionMapper {
  private static final Map<ActionEnum, Supplier<AbstractAction>> actionMap =
      Map.ofEntries(
          Map.entry(ActionEnum.AVANCER, ForwardAction::new),
          Map.entry(ActionEnum.GAUCHE, RotationLeftAction::new),
          Map.entry(ActionEnum.DROITE, RotationRightAction::new));

  public static AbstractAction mapToAction(@NonNull ActionEnum e) {
    return actionMap.get(e).get();
  }
}
