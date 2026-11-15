package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;
import java.util.UUID;

/**
 * Абстрактний батьківський клас Одяг (Clothes), що реалізує Comparable та Identifiable.
 */
public abstract class Clothes implements Comparable<Clothes>, Identifiable {
    protected UUID uuid;
    protected String name;
    protected String size;
    protected double price;
    protected int quantity;
    protected ClothingCategory category;

    public Clothes(String name, String size, double price, int quantity, ClothingCategory category) {
        this.uuid = UUID.randomUUID(); // Автоматична генерація UUID
        setName(name);
        setSize(size);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва не може бути порожньою");
        }
        this.name = name;
    }

    public String getSize() { return size; }
    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Розмір не може бути порожнім");
        }
        this.size = size;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною");
        }
        this.price = price;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Кількість не може бути від'ємною");
        }
        this.quantity = quantity;
    }

    public ClothingCategory getCategory() { return category; }
    public void setCategory(ClothingCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Категорія не може бути null");
        }
        this.category = category;
    }

    /**
     * Коротка інформація для відображення в списку (наприклад, для GUI).
     */
    public String getShortInfo() {
        return getClass().getSimpleName() + ": " + name + " | UUID: " + uuid;
    }

    @Override
    public int compareTo(Clothes other) {
        if (other == null) {
            return 1;
        }
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return Double.compare(clothes.price, price) == 0 &&
                name.equals(clothes.name) &&
                size.equals(clothes.size) &&
                category == clothes.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, size, price, category);
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category.getDisplayName() +
                '}';
    }
}