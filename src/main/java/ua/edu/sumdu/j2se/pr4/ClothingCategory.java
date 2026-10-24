package ua.edu.sumdu.j2se.pr4;

/**
 * Перерахування категорій одягу.
 */
public enum ClothingCategory {
    OUTERWEAR("Верхній одяг"),
    UNDERWEAR("Білизна"),
    FOOTWEAR("Взуття"),
    ACCESSORIES("Аксесуари");

    private final String displayName;

    ClothingCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}