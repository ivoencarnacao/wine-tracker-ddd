package dev.ivoencarnacao.winetracker.domain.vo;

import org.junit.jupiter.api.Test;

import dev.ivoencarnacao.winetracker.domain.errors.DomainErrorCode;
import dev.ivoencarnacao.winetracker.domain.errors.DomainException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BrandTest {

  @Test
  void shouldCreateWithValidName() {
    var brand = new Brand("Contemporal");

    assertThat(brand.name()).isEqualTo("Contemporal");
  }

  @Test
  void shouldTrimName() {
    var brand = new Brand("  Contemporal  ");

    assertThat(brand.name()).isEqualTo("Contemporal");
  }

  @Test
  void shouldRejectNullName() {
    assertThatThrownBy(() -> new Brand(null))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.BRAND_REQUIRED));
  }

  @Test
  void shouldRejectBlankName() {
    assertThatThrownBy(() -> new Brand("  "))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.BRAND_REQUIRED));
  }

  @Test
  void shouldRejectTooLongName() {
    String tooLong = "a".repeat(101);

    assertThatThrownBy(() -> new Brand(tooLong))
        .isInstanceOfSatisfying(DomainException.class,
            ex -> assertThat(ex.code()).isEqualTo(DomainErrorCode.BRAND_TOO_LONG));
  }
}
