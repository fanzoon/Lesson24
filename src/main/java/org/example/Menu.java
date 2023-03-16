package org.example;

import java.util.Scanner;

public class Menu {
    public static void menu (){
        int menu;
        Scanner scanner = new Scanner(System.in);
        Service service = new Service();
        startMenu();
        do {
            menu = scanner.nextInt();
            switch (menu) {
                case 0 -> startMenu();
                case 1 -> {
                    service.getAllStudents();
                    startMenu();
                }
                case 2 -> {
                    Service.getAllCities();
                    startMenu();
                }
                case 3 -> {
                    service.addStudent();
                    startMenu();
                }
                case 4 -> {
                    service.addCity();
                    startMenu();
                }
                case 5 -> {
                    service.deleteStudent();
                    startMenu();
                }
                case 6 -> {
                    service.deleteCity();
                    startMenu();
                }
                case 9 -> System.out.println("Выход из программы");
                default -> System.out.println("Выбрали несуществующий пункт меню");
            }
        } while (menu != 9);
    }

    private static void startMenu() {
        System.out.println("Меню программы:");
        System.out.println("Нажми 0, чтобы Вернуться в главное меню:");
        System.out.println("Нажми 1, чтобы посмотреть всех студентов");
        System.out.println("Нажми 2, чтобы посмотреть всех города");
        System.out.println("Нажми 3, чтобы добавить студента");
        System.out.println("Нажми 4, чтобы добавить город");
        System.out.println("Нажми 5, чтобы удалить студента");
        System.out.println("Нажми 6, чтобы удалить город");
        System.out.println("Нажми 9, чтобы выйти из приложения");
    }

}
