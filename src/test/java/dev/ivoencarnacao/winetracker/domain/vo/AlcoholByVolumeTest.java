package dev.ivoencarnacao.winetracker.domain.vo;

import org.junit.jupiter.api.Test;

import dev.ivoencarnacao.winetracker.domain.errors.DomainErrorCode;
import dev.ivoencarnacao.winetracker.domain.errors.DomainException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AlcoholByVolumeTest {

  @Test
  void shouldCreateWithValidValue() {
    var abv = new AlcoholByVolume(12.5);

    assertThat(abv.value()).isEqualTo(12.5);
  }

  @Test
  void shouldRejectNaNValue() {

    assertThatThrownBy(() -> new AlcoholByVolume(Double.NaN))
        .isInstanceOf(DomainException.class)
        .extracting(e -> ((DomainException) e).code())
        .isEqualTo(DomainErrorCode.INVALID_ABV_VALUE);

  }

  @Test
  void shouldRejectInfiniteValue() {

    assertThatThrownBy(() -> new AlcoholByVolume(Double.POSITIVE_INFINITY))
        .isInstanceOf(DomainException.class)
        .extracting(e -> ((DomainException) e).code())
        .isEqualTo(DomainErrorCode.INVALID_ABV_VALUE);

    assertThatThrownBy(() -> new AlcoholByVolume(Double.NEGATIVE_INFINITY))
        .isInstanceOf(DomainException.class)
        .extracting(e -> ((DomainException) e).code())
        .isEqualTo(DomainErrorCode.INVALID_ABV_VALUE);
  }

  @Test
  void shouldRejectOutOfRangeValues() {
    assertThatThrownBy(() -> new AlcoholByVolume(0.0))
        .isInstanceOf(DomainException.class)
        .extracting(e -> ((DomainException) e).code())
        .isEqualTo(DomainErrorCode.ABV_OUT_OF_RANGE);

    assertThatThrownBy(() -> new AlcoholByVolume(30.000_000_1))
        .isInstanceOf(DomainException.class)
        .extracting(e -> ((DomainException) e).code())
        .isEqualTo(DomainErrorCode.ABV_OUT_OF_RANGE);
  }

}
