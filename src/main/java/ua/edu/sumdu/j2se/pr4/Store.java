package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;

/**
 * Клас-контейнер Store (Магазин), що реалізує агрегацію колекції одягу
 * та управляє кількістю товарів і пошуком.
 */
public class Store {
    private String storeName;
    private ArrayList<Clothes> clothesList;

    public Store(String storeName) {
        this.storeName = storeName;
        this.clothesList = new ArrayList<>();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public ArrayList<Clothes> getClothesList() {
        return clothesList;
    }

    /**
     * Додає новий одяг до магазину або збільшує кількість існуючого товару.
     */
    public void addNewClothes(Clothes cl, int quantity) {
        if (cl == null || quantity <= 0) {
            return;
        }

        boolean found = false;
        for (Clothes item : clothesList) {
            // Перевіряємо за допомогою equals, чи такий товар вже існує
            if (item.equals(cl)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            // Якщо товару ще немає, встановлюємо передану кількість і додаємо
            cl.setQuantity(quantity);
            clothesList.add(cl);
        }
    }

    /**
     * Пошук об'єктів за категорією.
     */
    public ArrayList<Clothes> searchByCategory(ClothingCategory category) {
        ArrayList<Clothes> result = new ArrayList<>();
        for (Clothes item : clothesList) {
            if (item.getCategory() == category) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Пошук об'єктів за максимальною ціною.
     */
    public ArrayList<Clothes> searchByMaxPrice(double maxPrice) {
        ArrayList<Clothes> result = new ArrayList<>();
        for (Clothes item : clothesList) {
            if (item.getPrice() <= maxPrice) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Пошук об'єктів за ключовим словом у назві.
     */
    public ArrayList<Clothes> searchByNameKeyword(String keyword) {
        ArrayList<Clothes> result = new ArrayList<>();
        if (keyword == null) {
            return result;
        }
        String lowerKeyword = keyword.toLowerCase();
        for (Clothes item : clothesList) {
            if (item.getName().toLowerCase().contains(lowerKeyword)) {
                result.add(item);
            }
        }
        return result;
    }
}