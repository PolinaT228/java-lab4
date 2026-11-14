package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Головний клас-драйвер для демонстрації успадкування та поліморфізму.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(" ПРАКТИЧНА РОБОТА №7");
        System.out.println(" Тема: Наслідування, поліморфізм, колекції ArrayList");
        System.out.println(" Студентка групи ІН-41/2: Таратун Поліна");

        Scanner scanner = new Scanner(System.in);
        // Поліморфна колекція базового типу Clothes
        ArrayList<Clothes> clothesList = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== КОНСОЛЬНЕ МЕНЮ ===");
            System.out.println("1. Створити звичайний об’єкт одягу (Clothes)");
            System.out.println("2. Створити штани (Pants)");
            System.out.println("3. Створити сорочку (Shirts)");
            System.out.println("4. Вивести інформацію про всі об’єкти (поліморфізм)");
            System.out.println("5. Завершити роботу");
            System.out.print("Оберіть пункт меню (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Помилка! Введіть число від 1 до 5.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    try {
                        System.out.print("Введіть назву: ");
                        String name = scanner.nextLine();

                        System.out.print("Введіть розмір: ");
                        String size = scanner.nextLine();

                        System.out.print("Введіть ціну: ");
                        double price = Double.parseDouble(scanner.nextLine());

                        System.out.print("Введіть кількість: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        // Вибір категорії через enum
                        System.out.println("Оберіть категорію:");
                        ClothingCategory[] categories = ClothingCategory.values();
                        for (int i = 0; i < categories.length; i++) {
                            System.out.println((i + 1) + ". " + categories[i].getDisplayName());
                        }
                        System.out.print("Вибір (1-" + categories.length + "): ");
                        int catIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        ClothingCategory category = categories[catIndex];

                        // Створення залежно від обраного пункту меню
                        if (choice == 1) {
                            Clothes item = new Clothes(name, size, price, quantity, category);
                            clothesList.add(item);
                            System.out.println("Звичайний одяг успішно створено!");
                        } else if (choice == 2) {
                            System.out.print("Чи є ремінь? (true/false): ");
                            boolean hasBelt = Boolean.parseBoolean(scanner.nextLine());
                            Pants pants = new Pants(name, size, price, quantity, category, hasBelt);
                            clothesList.add(pants);
                            System.out.println("Штани успішно створено!");
                        } else if (choice == 3) {
                            System.out.print("Довжина рукава (наприклад, довгий/короткий): ");
                            String sleeve = scanner.nextLine();
                            Shirts shirt = new Shirts(name, size, price, quantity, category, sleeve);
                            clothesList.add(shirt);
                            System.out.println("Сорочку успішно створено!");
                        }

                    } catch (Exception e) {
                        System.out.println("Помилка створення об'єкта: " + e.getMessage());
                    }
                    break;

                case 4:
                    if (clothesList.isEmpty()) {
                        System.out.println("Список одягу порожній.");
                    } else {
                        System.out.println("\n--- Список об'єктів (демонстрація поліморфізму) ---");
                        // Поліморфний вивід через посилання базового типу Clothes
                        for (Clothes item : clothesList) {
                            System.out.println(item.toString());
                        }
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