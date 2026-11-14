package ua.edu.sumdu.j2se.pr4;

/**
 * Похідний клас Шкарпетки (Socks).
 */
public class Socks extends Clothes {
    private String material; // матеріал (наприклад, бавовна, вовна)

    public Socks(String name, String size, double price, int quantity, ClothingCategory category, String material) {
        super(name, size, price, quantity, category);
        setMaterial(material);
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Матеріал не може бути порожнім");
        }
        this.material = material;
    }

    @Override
    public String toString() {
        return "Socks{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category.getDisplayName() +
                ", material='" + material + '\'' +
                '}';
    }
}