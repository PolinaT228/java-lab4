package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Клас-контейнер Store (Магазин), що реалізує агрегацію колекції одягу,
 * управління кількістю, пошук, сортування, модифікацію та видалення.
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
            if (item.equals(cl)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            cl.setQuantity(quantity);
            clothesList.add(cl);
        }
    }

    /**
     * Оновлює дані існуючого об'єкта в колекції.
     */
    public boolean update(Clothes existingObject, Clothes newObject) {
        if (existingObject == null || newObject == null) {
            return false;
        }

        for (int i = 0; i < clothesList.size(); i++) {
            Clothes item = clothesList.get(i);
            // Порівнюємо за UUID або за equals
            if (item.getUuid().equals(existingObject.getUuid()) || item.equals(existingObject)) {
                item.setName(newObject.getName());
                item.setSize(newObject.getSize());
                item.setPrice(newObject.getPrice());
                item.setQuantity(newObject.getQuantity());
                item.setCategory(newObject.getCategory());
                return true;
            }
        }
        return false;
    }

    /**
     * Видаляє об'єкт з колекції.
     */
    public boolean delete(Clothes existingObject) {
        if (existingObject == null) {
            return false;
        }

        return clothesList.removeIf(item ->
                item.getUuid().equals(existingObject.getUuid()) || item.equals(existingObject)
        );
    }

    /**
     * Повертає новий список товарів, відсортований за заданим Comparator.
     */
    public ArrayList<Clothes> getSortedClothesByComparator(Comparator<Clothes> comparator) {
        ArrayList<Clothes> sortedList = new ArrayList<>(clothesList);
        if (comparator != null) {
            sortedList.sort(comparator);
        }
        return sortedList;
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