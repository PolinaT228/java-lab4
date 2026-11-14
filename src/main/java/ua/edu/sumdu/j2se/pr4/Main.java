package ua.edu.sumdu.j2se.pr4;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Головний клас-драйвер із повним функціоналом ієрархії, меню та роботи з файлом input.txt.
 */
public class Main {
    private static final String FILE_NAME = "input.txt";

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println(" ПРАКТИЧНА РОБОТА №9");
        System.out.println(" Тема: Ієрархія та робота з файлами");
        System.out.println(" Студентка групи ІН-41/2: Таратун Поліна");
        System.out.println("==================================================\n");

        Scanner scanner = new Scanner(System.in);

        // Автоматичне зчитування даних з файлу input.txt при запуску програми
        ArrayList<Clothes> clothesList = loadFromFile();
        System.out.println("Дані успішно завантажено з файлу. Кількість об'єктів: " + clothesList.size());

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
                                creating = false;
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
                    // Автоматичне збереження актуального стану колекції у файл перед виходом
                    saveToFile(clothesList);
                    System.out.println("Дані успішно збережено у файл input.txt.");
                    running = false;
                    System.out.println("Роботу програми завершено.");
                    break;

                default:
                    System.out.println("Невірний вибір! Оберіть від 1 до 3.");
            }
        }
        scanner.close();
    }

    /**
     * Метод для збереження колекції об'єктів у файл input.txt.
     */
    public static void saveToFile(ArrayList<Clothes> clothesList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Clothes item : clothesList) {
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
     * Метод для завантаження колекції об'єктів з файлу input.txt.
     */
    public static ArrayList<Clothes> loadFromFile() {
        ArrayList<Clothes> clothesList = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return clothesList; // Якщо файл ще не створено, повертаємо порожню колекцію
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
                        item = new Pants(name, size, price, quantity, category, hasBelt);
                        break;
                    case "Shirts":
                        String sleeve = parts[6];
                        item = new Shirts(name, size, price, quantity, category, sleeve);
                        break;
                    case "Jacket":
                        boolean hasHood = Boolean.parseBoolean(parts[6]);
                        item = new Jacket(name, size, price, quantity, category, hasHood);
                        break;
                    case "Socks":
                        String material = parts[6];
                        item = new Socks(name, size, price, quantity, category, material);
                        break;
                    case "Clothes":
                        item = new Clothes(name, size, price, quantity, category);
                        break;
                }

                if (item != null) {
                    clothesList.add(item);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Помилка читання з файлу: " + e.getMessage());
        }
        return clothesList;
    }
}