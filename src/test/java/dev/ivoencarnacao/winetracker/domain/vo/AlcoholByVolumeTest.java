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
  void shouldAcceptMaximumValue() {
    var abv = new AlcoholByVolume(30.0);
    assertThat(abv.value()).isEqualTo(30.0);
  }

  @Test
  void shouldRejectNaNValue() {
    assertThatThrownBy(() -> new AlcoholByVolume(Double.NaN))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.INVALID_ABV_VALUE));
  }

  @Test
  void shouldRejectInfiniteValue() {
    assertThatThrownBy(() -> new AlcoholByVolume(Double.POSITIVE_INFINITY))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.INVALID_ABV_VALUE));

    assertThatThrownBy(() -> new AlcoholByVolume(Double.NEGATIVE_INFINITY))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.INVALID_ABV_VALUE));
  }

  @Test
  void shouldRejectZeroOrNegativeValues() {
    assertThatThrownBy(() -> new AlcoholByVolume(0.0))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.ABV_OUT_OF_RANGE));

    assertThatThrownBy(() -> new AlcoholByVolume(-0.1))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.ABV_OUT_OF_RANGE));
  }

  @Test
  void shouldRejectValuesAboveMaximum() {
    assertThatThrownBy(() -> new AlcoholByVolume(30.000_000_1))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.ABV_OUT_OF_RANGE));
  }

  @Test
  void shouldClassifyLowAndHighAlcoholCorrectly() {
    assertThat(new AlcoholByVolume(7.9).isLowAlcohol()).isTrue();
    assertThat(new AlcoholByVolume(8.0).isLowAlcohol()).isFalse();

    assertThat(new AlcoholByVolume(14.9).isHighAlcohol()).isFalse();
    assertThat(new AlcoholByVolume(15.0).isHighAlcohol()).isTrue();
  }

  @Test
  void shouldFormatAsPercentVolume() {
    assertThat(new AlcoholByVolume(12.5).toString()).isEqualTo("12.5% vol.");
  }
}
