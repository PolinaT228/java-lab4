package ua.edu.sumdu.j2se.pr4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Clothes> clothesList = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== КОНСОЛЬНЕ МЕНЮ ===");
            System.out.println("1. Створити новий об’єкт одягу");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу");
            System.out.print("Оберіть пункт меню (1-3): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Помилка! Будь ласка, введіть число від 1 до 3.");
                scanner.next(); // очистити невірне введення
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // очищення буфера

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Введіть назву: ");
                        String name = scanner.nextLine();

                        System.out.print("Введіть розмір: ");
                        String size = scanner.nextLine();

                        System.out.print("Введіть ціну: ");
                        double price = Double.parseDouble(scanner.nextLine());

                        System.out.print("Введіть кількість: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        Clothes item = new Clothes(name, size, price, quantity);
                        clothesList.add(item);
                        System.out.println("Об'єкт успішно створено та додано!");
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка введення числа! Спробуйте ще раз.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка валідації: " + e.getMessage());
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