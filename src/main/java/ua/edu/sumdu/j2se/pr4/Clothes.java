package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;

public class Clothes {
    private String name;
    private String size;
    private double price;
    private int quantity; // Нове поле (загалом 4 атрибути)

    // Конструктор з перевіркою валідності даних
    public Clothes(String name, String size, double price, int quantity) {
        setName(name);
        setSize(size);
        setPrice(price);
        setQuantity(quantity);
    }

    // Гетери та сетери з валідацією
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва не може бути порожньою або null");
        }
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Розмір не може бути порожнім або null");
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

    @Override
    public String toString() {
        return "Clothes{name='" + name + "', size='" + size + "', price=" + price + ", quantity=" + quantity + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return Double.compare(clothes.price, price) == 0 &&
                quantity == clothes.quantity &&
                Objects.equals(name, clothes.name) &&
                Objects.equals(size, clothes.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, price, quantity);
    }
}