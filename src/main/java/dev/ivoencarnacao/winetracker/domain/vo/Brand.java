package dev.ivoencarnacao.winetracker.domain.vo;

import java.util.Map;
import dev.ivoencarnacao.winetracker.domain.errors.DomainErrorCode;
import dev.ivoencarnacao.winetracker.domain.errors.DomainException;

public record Brand(String name) {

  private static final int MAX_LENGTH = 100;

  public Brand {

    if (name == null) {
      throw new DomainException(
          DomainErrorCode.BRAND_REQUIRED,
          "Brand is required.",
          Map.of("field", "name"));
    }

    name = name.trim();

    if (name.isEmpty()) {
      throw new DomainException(
        DomainErrorCode.BRAND_REQUIRED,
          "Brand cannot be blank.",
          Map.of("field", "name"));
    }

    if (name.length() > MAX_LENGTH) {
      throw new DomainException(
        DomainErrorCode.BRAND_TOO_LONG,
          "Brand is too long.",
          Map.of("field", "name", "length", name.length(), "maxLength", MAX_LENGTH));
    }
  }
}
