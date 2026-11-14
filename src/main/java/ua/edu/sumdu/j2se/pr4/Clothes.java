package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;

/**
 package ua.edu.sumdu.j2se.pr4;

 import java.util.Objects;

 /**
 * Базовий клас, що описує предметну область "Одяг".
 */
public class Clothes {
    protected String name;
    protected String size;
    protected double price;
    protected int quantity;
    protected ClothingCategory category;

    /**
     * Конструктор з параметрами та валідацією.
     */
    public Clothes(String name, String size, double price, int quantity, ClothingCategory category) {
        setName(name);
        setSize(size);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
    }

    // Гетери та сетери
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
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0");
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

    @Override
    public String toString() {
        return "Clothes{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category.getDisplayName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return Double.compare(clothes.price, price) == 0 &&
                quantity == clothes.quantity &&
                Objects.equals(name, clothes.name) &&
                Objects.equals(size, clothes.size) &&
                category == clothes.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, price, quantity, category);
    }
}git add .