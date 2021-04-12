package fr.carbonit.checker.exception;

import fr.carbonit.checker.Violation;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class ViolationsDetectedException extends CheckerException {
  public ViolationsDetectedException(@NonNull List<Violation> violations) {
    super(System.lineSeparator() + violations.stream().map(Violation::toString).collect(Collectors.joining(System.lineSeparator())));
  }
}
