package ua.edu.sumdu.j2se.pr4;

/**
 * Похідний клас Штани (Pants).
 */
public class Pants extends Clothes {
    private boolean hasBelt;

    public Pants(String name, String size, double price, int quantity, ClothingCategory category, boolean hasBelt) {
        super(name, size, price, quantity, category);
        this.hasBelt = hasBelt;
    }

    public boolean isHasBelt() { return hasBelt; }
    public void setHasBelt(boolean hasBelt) { this.hasBelt = hasBelt; }

    @Override
    public String toString() {
        return "Pants{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category.getDisplayName() +
                ", hasBelt=" + (hasBelt ? "так" : "ні") +
                '}';
    }
}