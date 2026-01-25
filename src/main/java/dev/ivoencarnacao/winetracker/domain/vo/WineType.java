package dev.ivoencarnacao.winetracker.domain.vo;

public enum WineType {
  STILL,
  SPARKLING,
  DESSERT,
  FORTIFIED;

  public static WineType fromLabel(String label) {
    return switch (label.trim().toLowerCase()) {
      case "still" -> STILL;
      case "sparkling" -> SPARKLING;
      case "dessert", "late harvest", "colheita tardia", "licoroso", "moscatel de setúbal" -> DESSERT;
      case "fortified", "fortificado", "vinho do porto" -> FORTIFIED;
      default -> throw new IllegalArgumentException(
        "Unknown wine type: " + label);
    };
  }
}
