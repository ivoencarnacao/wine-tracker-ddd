package dev.ivoencarnacao.winetracker.domain.vo;

import java.util.Map;

import dev.ivoencarnacao.winetracker.domain.errors.DomainErrorCode;
import dev.ivoencarnacao.winetracker.domain.errors.DomainException;

public record AlcoholByVolume(double value) {

  private static final double MIN_EXCLUSIVE = 0.0;
  private static final double MAX_INCLUSIVE = 30.0;

  public AlcoholByVolume {
    if (Double.isNaN(value) || Double.isInfinite(value)) {
      throw new DomainException(
          DomainErrorCode.INVALID_ABV_VALUE,
          "Invalid alcohol by volume value.",
          Map.of("value", value));
    }

    if (value <= MIN_EXCLUSIVE || value > MAX_INCLUSIVE) {
      throw new DomainException(
          DomainErrorCode.ABV_OUT_OF_RANGE,
          "Alcohol by volume must be between 0 and 30% vol.",
          Map.of("value", value, "minExclusive", MIN_EXCLUSIVE, "maxInclusive", MAX_INCLUSIVE));
    }
  }

  public boolean isLowAlcohol() {
    return value < 8.0;
  }

  public boolean isHighAlcohol() {
    return value >= 15.0;
  }

  @Override
  public String toString() {
    return value + "% vol.";
  }

}
