package ua.edu.sumdu.j2se.pr4;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Головний клас-драйвер для Практичної роботи №11 (Агрегація через клас Store).
 */
public class Main {
    private static final String FILE_NAME = "input.txt";

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println(" ПРАКТИЧНА РОБОТА №11");
        System.out.println(" Тема: Колекції, агрегація, класи-обгортки");
        System.out.println(" Студентка групи ІН-41/2: Таратун Поліна");
        System.out.println("==================================================\n");

        Scanner scanner = new Scanner(System.in);

        // Створюємо об'єкт магазину (агрегація)
        Store myStore = new Store("Модний Стиль");

        // Автоматичне зчитування даних з файлу input.txt при запуску програми
        loadStoreFromFile(myStore);
        System.out.println("Магазин \"" + myStore.getStoreName() + "\" ініціалізовано. Товарів у каталозі: " + myStore.getClothesList().size());

        boolean running = true;

        while (running) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ МАГАЗИНУ ===");
            System.out.println("1. Пошук товару");
            System.out.println("2. Додати новий товар (або оновити кількість)");
            System.out.println("3. Вивести інформацію про всі товари");
            System.out.println("4. Завершити роботу програми");
            System.out.print("Оберіть пункт меню (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Помилка! Введіть число від 1 до 4.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleSearchMenu(myStore, scanner);
                    break;

                case 2:
                    boolean creating = true;
                    while (creating) {
                        System.out.println("\n--- ВИБІР ТИПУ ТОВАРУ ---");
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
                            creating = false;
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

                            System.out.print("Введіть кількість для додавання: ");
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
                                    newItem = new Clothes(name, size, price, 1, category);
                                    break;
                                case 2:
                                    System.out.print("Чи є ремінь? (true/false): ");
                                    boolean hasBelt = Boolean.parseBoolean(scanner.nextLine());
                                    newItem = new Pants(name, size, price, 1, category, hasBelt);
                                    break;
                                case 3:
                                    System.out.print("Довжина рукава: ");
                                    String sleeve = scanner.nextLine();
                                    newItem = new Shirts(name, size, price, 1, category, sleeve);
                                    break;
                                case 4:
                                    System.out.print("Чи є капюшон? (true/false): ");
                                    boolean hasHood = Boolean.parseBoolean(scanner.nextLine());
                                    newItem = new Jacket(name, size, price, 1, category, hasHood);
                                    break;
                                case 5:
                                    System.out.print("Введіть матеріал: ");
                                    String material = scanner.nextLine();
                                    newItem = new Socks(name, size, price, 1, category, material);
                                    break;
                            }

                            if (newItem != null) {
                                // Використовуємо метод магазину згідно з вимогами завдань
                                myStore.addNewClothes(newItem, quantity);
                                System.out.println("Товар успішно додано (або оновлено його кількість) через Store!");
                                creating = false;
                            }

                        } catch (Exception e) {
                            System.out.println("Помилка створення об'єкта: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    if (myStore.getClothesList().isEmpty()) {
                        System.out.println("Каталог магазину порожній.");
                    } else {
                        System.out.println("\n--- Список усіх товарів у магазині ---");
                        for (Clothes item : myStore.getClothesList()) {
                            System.out.println(item.toString());
                        }
                    }
                    break;

                case 4:
                    // Збереження стану магазину у файл перед виходом
                    saveStoreToFile(myStore);
                    System.out.println("Дані магазину успішно збережено у файл input.txt.");
                    running = false;
                    System.out.println("Роботу програми завершено.");
                    break;

                default:
                    System.out.println("Невірний вибір! Оберіть від 1 до 4.");
            }
        }
        scanner.close();
    }

    /**
     * Підменю пошуку товарів через методи класу Store.
     */
    private static void handleSearchMenu(Store store, Scanner scanner) {
        boolean searching = true;
        while (searching) {
            System.out.println("\n=== ПІДМЕНЮ ПОШУКУ ===");
            System.out.println("1. Пошук за категорією одягу");
            System.out.println("2. Пошук за максимальною ціною");
            System.out.println("3. Пошук за назвою (частковий збіг)");
            System.out.println("4. Повернутися до головного меню");
            System.out.print("Оберіть критерій пошуку (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Помилка! Введіть число від 1 до 4.");
                scanner.next();
                continue;
            }

            int searchChoice = scanner.nextInt();
            scanner.nextLine();

            switch (searchChoice) {
                case 1:
                    System.out.println("Оберіть категорію для пошуку:");
                    ClothingCategory[] categories = ClothingCategory.values();
                    for (int i = 0; i < categories.length; i++) {
                        System.out.println((i + 1) + ". " + categories[i].getDisplayName());
                    }
                    System.out.print("Вибір (1-" + categories.length + "): ");
                    if (scanner.hasNextInt()) {
                        int catIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (catIndex >= 0 && catIndex < categories.length) {
                            ArrayList<Clothes> result = store.searchByCategory(categories[catIndex]);
                            printSearchResults(result);
                        } else {
                            System.out.println("Невірний номер категорії!");
                        }
                    } else {
                        System.out.println("Помилка введення!");
                        scanner.next();
                    }
                    break;

                case 2:
                    System.out.print("Введіть максимальну ціну: ");
                    if (scanner.hasNextDouble()) {
                        double maxPrice = scanner.nextDouble();
                        scanner.nextLine();
                        ArrayList<Clothes> result = store.searchByMaxPrice(maxPrice);
                        printSearchResults(result);
                    } else {
                        System.out.println("Помилка введення ціни!");
                        scanner.next();
                    }
                    break;

                case 3:
                    System.out.print("Введіть частину назви для пошуку: ");
                    String keyword = scanner.nextLine();
                    ArrayList<Clothes> result = store.searchByNameKeyword(keyword);
                    printSearchResults(result);
                    break;

                case 4:
                    searching = false;
                    break;

                default:
                    System.out.println("Невірний вибір! Оберіть від 1 до 4.");
            }
        }
    }

    private static void printSearchResults(ArrayList<Clothes> result) {
        if (result.isEmpty()) {
            System.out.println("\n[Результат пошуку]: Жоден товар не відповідає умовам пошуку.");
        } else {
            System.out.println("\n[Результат пошуку] Знайдено товарів: " + result.size());
            for (Clothes item : result) {
                System.out.println(item.toString());
            }
        }
    }

    /**
     * Збереження даних магазину у файл input.txt.
     */
    public static void saveStoreToFile(Store store) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Clothes item : store.getClothesList()) {
                StringBuilder sb = new StringBuilder();
                if (item instanceof Pants) {
                    sb.append("Pants;").append(item.getName()).append(";")
                            .append(item.getSize()).append(";")
                            .append(item.getPrice()).append(";")
                            .append(item.getQuantity()).append(";")
                            .append(item.getCategory().name()).append(";")
                            .append(((Pants) item).isHasBelt());
                } else if (item instanceof Shirts) {
                    sb.append("Shirts;").append(item.getName()).append(";")
                            .append(item.getSize()).append(";")
                            .append(item.getPrice()).append(";")
                            .append(item.getQuantity()).append(";")
                            .append(item.getCategory().name()).append(";")
                            .append(((Shirts) item).getSleeveLength());
                } else if (item instanceof Jacket) {
                    sb.append("Jacket;").append(item.getName()).append(";")
                            .append(item.getSize()).append(";")
                            .append(item.getPrice()).append(";")
                            .append(item.getQuantity()).append(";")
                            .append(item.getCategory().name()).append(";")
                            .append(((Jacket) item).isHasHood());
                } else if (item instanceof Socks) {
                    sb.append("Socks;").append(item.getName()).append(";")
                            .append(item.getSize()).append(";")
                            .append(item.getPrice()).append(";")
                            .append(item.getQuantity()).append(";")
                            .append(item.getCategory().name()).append(";")
                            .append(((Socks) item).getMaterial());
                } else {
                    sb.append("Clothes;").append(item.getName()).append(";")
                            .append(item.getSize()).append(";")
                            .append(item.getPrice()).append(";")
                            .append(item.getQuantity()).append(";")
                            .append(item.getCategory().name());
                }
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    /**
     * Завантаження даних магазину з файлу input.txt за допомогою методу addNewClothes.
     */
    public static void loadStoreFromFile(Store store) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length < 6) {
                    continue;
                }

                String type = parts[0];
                String name = parts[1];
                String size = parts[2];
                double price = Double.parseDouble(parts[3]);
                int quantity = Integer.parseInt(parts[4]);
                ClothingCategory category = ClothingCategory.valueOf(parts[5]);

                Clothes item = null;
                switch (type) {
                    case "Pants":
                        boolean hasBelt = Boolean.parseBoolean(parts[6]);
                        item = new Pants(name, size, price, 1, category, hasBelt);
                        break;
                    case "Shirts":
                        String sleeve = parts[6];
                        item = new Shirts(name, size, price, 1, category, sleeve);
                        break;
                    case "Jacket":
                        boolean hasHood = Boolean.parseBoolean(parts[6]);
                        item = new Jacket(name, size, price, 1, category, hasHood);
                        break;
                    case "Socks":
                        String material = parts[6];
                        item = new Socks(name, size, price, 1, category, material);
                        break;
                    case "Clothes":
                        item = new Clothes(name, size, price, 1, category);
                        break;
                }

                if (item != null) {
                    // Використовуємо метод addNewClothes згідно з варіантом
                    store.addNewClothes(item, quantity);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Помилка читання з файлу: " + e.getMessage());
        }
    }
}