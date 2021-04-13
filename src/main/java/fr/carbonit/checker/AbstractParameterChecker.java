package fr.carbonit.checker;

import fr.carbonit.model.objects.GameObject;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class AbstractParameterChecker<T extends GameObject> {
  protected abstract @NonNull List<Function<T, Violation>> getChecks();

  public @NonNull List<Violation> checkParameters(@NonNull List<T> parameters) {
    return parameters.stream()
        .flatMap(
            param -> getChecks().stream().map(check -> check.apply(param)).filter(Objects::nonNull))
        .collect(Collectors.toList());
  }
}
