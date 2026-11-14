package ua.edu.sumdu.j2se.pr4;

/**
 * Похідний клас Сорочки (Shirts).
 */
public class Shirts extends Clothes {
    private String sleeveLength; // наприклад, "довгий" або "короткий"

    public Shirts(String name, String size, double price, int quantity, ClothingCategory category, String sleeveLength) {
        super(name, size, price, quantity, category);
        setSleeveLength(sleeveLength);
    }

    public String getSleeveLength() { return sleeveLength; }
    public void setSleeveLength(String sleeveLength) {
        if (sleeveLength == null || sleeveLength.trim().isEmpty()) {
            throw new IllegalArgumentException("Довжина рукава не може бути порожньою");
        }
        this.sleeveLength = sleeveLength;
    }

    @Override
    public String toString() {
        return "Shirts{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category.getDisplayName() +
                ", sleeveLength='" + sleeveLength + '\'' +
                '}';
    }
}