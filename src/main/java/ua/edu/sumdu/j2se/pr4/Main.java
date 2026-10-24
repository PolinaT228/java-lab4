package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Головний клас-драйвер для запуску програми.
 */
public class Main {
    public static void main(String[] args) {
        // Інформаційна шапка програми
        System.out.println(" ПРАКТИЧНА РОБОТА №6");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Clothes> clothesList = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== КОНСОЛЬНЕ МЕНЮ ===");
            System.out.println("1. Створити новий об’єкт одягу");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Перевірити статичний лічильник об’єктів");
            System.out.println("4. Продемонструвати конструктор копіювання");
            System.out.println("5. Завершити роботу");
            System.out.print("Оберіть пункт меню (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Помилка! Будь ласка, введіть число від 1 до 5.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Введіть назву одягу: ");
                        String name = scanner.nextLine();

                        System.out.print("Введіть розмір: ");
                        String size = scanner.nextLine();

                        System.out.print("Введіть ціну: ");
                        double price = Double.parseDouble(scanner.nextLine());

                        System.out.print("Введіть кількість: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        System.out.println("Оберіть категорію одягу:");
                        ClothingCategory[] categories = ClothingCategory.values();
                        for (int i = 0; i < categories.length; i++) {
                            System.out.println((i + 1) + ". " + categories[i].getDisplayName());
                        }
                        System.out.print("Ваш вибір (1-" + categories.length + "): ");
                        int catIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        ClothingCategory category = categories[catIndex];

                        System.out.print("Введіть назву бренду (виробника): ");
                        String brand = scanner.nextLine();

                        System.out.print("Введіть країну виробника: ");
                        String country = scanner.nextLine();

                        Manufacturer manufacturer = new Manufacturer(brand, country);
                        Clothes item = new Clothes(name, size, price, quantity, category, manufacturer);
                        clothesList.add(item);
                        System.out.println("Об'єкт успішно створено!");
                    } catch (Exception e) {
                        System.out.println("Помилка створення об'єкта: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (clothesList.isEmpty()) {
                        System.out.println("Список одягу порожній.");
                    } else {
                        System.out.println("\n--- Список створених об'єктів одягу ---");
                        for (Clothes item : clothesList) {
                            System.out.println(item.toString());
                        }
                    }
                    break;

                case 3:
                    System.out.println("Загальна кількість створених об'єктів (через статичний метод): "
                            + Clothes.getCreatedObjectsCount());
                    break;

                case 4:
                    if (clothesList.isEmpty()) {
                        System.out.println("Спочатку створіть хоча б один об'єкт через пункт 1.");
                    } else {
                        Clothes original = clothesList.get(0);
                        Clothes copy = new Clothes(original);
                        System.out.println("\nОригінал: " + original);
                        System.out.println("Копія (через конструктор копіювання): " + copy);
                        System.out.println("Чи це посилаються на один об'єкт? " + (original == copy));
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Роботу програми завершено.");
                    break;

                default:
                    System.out.println("Невірний вибір! Оберіть від 1 до 5.");
            }
        }
        scanner.close();
    }
}