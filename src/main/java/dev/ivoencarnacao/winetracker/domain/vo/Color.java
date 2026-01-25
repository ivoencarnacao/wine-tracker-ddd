package dev.ivoencarnacao.winetracker.domain.vo;

public enum Color {
  RED,
  WHITE,
  ROSE;

  public static Color fromLabel(String label) {
    return switch (label.trim().toLowerCase()) {
      case "red", "tinto" -> RED;
      case "white", "branco" -> WHITE;
      case "rose", "rosé" -> ROSE;
      default -> throw new IllegalArgumentException(
          "Unknown color: " + label);
    };
  }
}
