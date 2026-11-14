package ua.edu.sumdu.j2se.pr4;

/**
 * Похідний клас Куртка (Jacket).
 */
public class Jacket extends Clothes {
    private boolean hasHood; // наявність капюшона

    public Jacket(String name, String size, double price, int quantity, ClothingCategory category, boolean hasHood) {
        super(name, size, price, quantity, category);
        this.hasHood = hasHood;
    }

    public boolean isHasHood() { return hasHood; }
    public void setHasHood(boolean hasHood) { this.hasHood = hasHood; }

    @Override
    public String toString() {
        return "Jacket{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category.getDisplayName() +
                ", hasHood=" + (hasHood ? "так" : "ні") +
                '}';
    }
}