package dev.ivoencarnacao.winetracker.domain.errors;

import java.util.Map;
import java.util.Objects;

public class DomainException extends RuntimeException {

  private final DomainErrorCode code;
  private final Map<String, Object> details;

  public DomainException(DomainErrorCode code, String message) {
    this(code, message, Map.of());
  }

  public DomainException(DomainErrorCode code, String message, Map<String, Object> details) {
    super(Objects.requireNonNull(message, "message is required."));
    this.code = Objects.requireNonNull(code, "code is required.");
    this.details = details == null ? Map.of() : Map.copyOf(details);
  }

  public DomainErrorCode code() {
    return code;
  }

  public Map<String, Object> details() {
    return details;
  }

}
