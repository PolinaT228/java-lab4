package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Головний клас-драйвер із розширеним консольним меню.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(" ПРАКТИЧНА РОБОТА №8");
        System.out.println(" Тема: Розширення ієрархії класів та поліморфне меню");
        System.out.println(" Студентка групи ІН-41/2: Таратун Поліна");

        Scanner scanner = new Scanner(System.in);
        // Внутрішня колекція за замовчуванням порожня
        ArrayList<Clothes> clothesList = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу програми");
            System.out.print("Оберіть пункт меню (1-3): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Помилка! Введіть число від 1 до 3.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Підменю створення нового об'єкта
                    boolean creating = true;
                    while (creating) {
                        System.out.println("\n--- ВИБІР ТИПУ ОБ'ЄКТА ---");
                        System.out.println("1. Базовий одяг (Clothes)");
                        System.out.println("2. Штани (Pants)");
                        System.out.println("3. Сорочка (Shirts)");
                        System.out.println("4. Куртка (Jacket)");
                        System.out.println("5. Шкарпетки (Socks)");
                        System.out.println("6. Повернутися до головного меню");
                        System.out.print("Оберіть тип (1-6): ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Помилка! Введіть число від 1 до 6.");
                            scanner.next();
                            continue;
                        }

                        int subChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (subChoice == 6) {
                            creating = false; // Повернення до головного меню
                            break;
                        }

                        if (subChoice < 1 || subChoice > 5) {
                            System.out.println("Невірний вибір! Спробуйте ще раз.");
                            continue;
                        }

                        try {
                            System.out.print("Введіть назву: ");
                            String name = scanner.nextLine();

                            System.out.print("Введіть розмір: ");
                            String size = scanner.nextLine();

                            System.out.print("Введіть ціну: ");
                            double price = Double.parseDouble(scanner.nextLine());

                            System.out.print("Введіть кількість: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            System.out.println("Оберіть категорію:");
                            ClothingCategory[] categories = ClothingCategory.values();
                            for (int i = 0; i < categories.length; i++) {
                                System.out.println((i + 1) + ". " + categories[i].getDisplayName());
                            }
                            System.out.print("Вибір (1-" + categories.length + "): ");
                            int catIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            ClothingCategory category = categories[catIndex];

                            Clothes newItem = null;

                            switch (subChoice) {
                                case 1:
                                    newItem = new Clothes(name, size, price, quantity, category);
                                    break;
                                case 2:
                                    System.out.print("Чи є ремінь? (true/false): ");
                                    boolean hasBelt = Boolean.parseBoolean(scanner.nextLine());
                                    newItem = new Pants(name, size, price, quantity, category, hasBelt);
                                    break;
                                case 3:
                                    System.out.print("Довжина рукава: ");
                                    String sleeve = scanner.nextLine();
                                    newItem = new Shirts(name, size, price, quantity, category, sleeve);
                                    break;
                                case 4:
                                    System.out.print("Чи є капюшон? (true/false): ");
                                    boolean hasHood = Boolean.parseBoolean(scanner.nextLine());
                                    newItem = new Jacket(name, size, price, quantity, category, hasHood);
                                    break;
                                case 5:
                                    System.out.print("Введіть матеріал: ");
                                    String material = scanner.nextLine();
                                    newItem = new Socks(name, size, price, quantity, category, material);
                                    break;
                            }

                            if (newItem != null) {
                                clothesList.add(newItem);
                                System.out.println("Об'єкт успішно створено та додано до колекції!");
                                creating = false; // Вихід у головне меню після успішного створення
                            }

                        } catch (Exception e) {
                            System.out.println("Помилка створення об'єкта: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    if (clothesList.isEmpty()) {
                        System.out.println("Колекція одягу порожня.");
                    } else {
                        System.out.println("\n--- Список усіх об'єктів у колекції ---");
                        for (Clothes item : clothesList) {
                            System.out.println(item.toString());
                        }
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Роботу програми завершено.");
                    break;

                default:
                    System.out.println("Невірний вибір! Оберіть від 1 до 3.");
            }
        }
        scanner.close();
    }
}