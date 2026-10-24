package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;

/**
 * Клас, що описує предметну область "Одяг".
 */
public class Clothes {
    // Статичне поле для підрахунку кількості створених об'єктів
    private static int createdObjectsCount = 0;

    private String name;
    private String size;
    private double price;
    private int quantity;
    private ClothingCategory category; // Використання enum
    private Manufacturer manufacturer; // Агрегація іншого класу

    /**
     * Повний конструктор з параметрами та валідацією.
     */
    public Clothes(String name, String size, double price, int quantity, ClothingCategory category, Manufacturer manufacturer) {
        setName(name);
        setSize(size);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
        setManufacturer(manufacturer);
        createdObjectsCount++; // Збільшуємо лічильник при створенні об'єкта
    }

    /**
     * Конструктор копіювання.
     * @param other інший об'єкт Clothes для копіювання
     */
    public Clothes(Clothes other) {
        if (other != null) {
            this.name = other.name;
            this.size = other.size;
            this.price = other.price;
            this.quantity = other.quantity;
            this.category = other.category;
            // Глибоке копіювання агрегованого об'єкта
            this.manufacturer = other.manufacturer != null ? new Manufacturer(other.manufacturer) : null;
            createdObjectsCount++;
        }
    }

    /**
     * Статичний гетер для отримання кількості створених об'єктів.
     * @return кількість створених об'єктів
     */
    public static int getCreatedObjectsCount() {
        return createdObjectsCount;
    }

    // Гетери та сетери з перевірками
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва не може бути порожньою");
        }
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Розмір не може бути порожнім");
        }
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Кількість не може бути від'ємною");
        }
        this.quantity = quantity;
    }

    public ClothingCategory getCategory() {
        return category;
    }

    public void setCategory(ClothingCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Категорія не може бути null");
        }
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        if (manufacturer == null) {
            throw new IllegalArgumentException("Виробник не може бути null");
        }
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category.getDisplayName() +
                ", manufacturer=" + manufacturer +
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
                category == clothes.category &&
                Objects.equals(manufacturer, clothes.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, price, quantity, category, manufacturer);
    }
}