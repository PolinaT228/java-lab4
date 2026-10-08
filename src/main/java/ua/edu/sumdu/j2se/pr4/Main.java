package ua.edu.sumdu.j2se.pr4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість елементів (одягу): ");
        int count = scanner.nextInt();
        scanner.nextLine(); // очищення буфера

        Clothes[] clothesArray = new Clothes[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nВведення даних для одягу #" + (i + 1));

            System.out.print("Введіть назву: ");
            String name = scanner.nextLine();

            System.out.print("Введіть розмір: ");
            String size = scanner.nextLine();

            System.out.print("Введіть ціну: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // очищення буфера

            clothesArray[i] = new Clothes(name, size, price);
        }

        System.out.println("\n--- Список створених об'єктів одягу ---");
        for (Clothes item : clothesArray) {
            System.out.println(item.toString());
        }

        scanner.close();
    }
}