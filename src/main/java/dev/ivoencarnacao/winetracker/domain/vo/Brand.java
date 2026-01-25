package dev.ivoencarnacao.winetracker.domain.vo;

import java.util.Objects;

public record Brand(String name) {

  private static final int MAX_LENGTH = 100;

  public Brand {

    Objects.requireNonNull(name, "Brand is required");
    name = name.trim();

    if (name.isEmpty()) {
      throw new IllegalArgumentException("Brand cannot be blank.");
    }

    if (name.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("Brand is too long.");
    }
  }
}
