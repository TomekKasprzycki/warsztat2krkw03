package pl.coderslab.warsztat2krkw03.controller;

import java.util.Scanner;

public class UserController {

    public static void main(String[] args) {

        System.out.println("Witaj w programie narzędziowym USER!");
        Scanner scan = new Scanner(System.in);

        while(true){
            displayMenu();
            final String option = scan.nextLine();

            if (option.equals("1")){
                addUser();
            } else if (option.equals("2")){
                editUser();
            } else if (option.equals("3")){
                deleteUser();
            } else if (option.equals("0")){
                System.out.println("Zakończono działanie programu. Do zobacznia!");
            } else {
                System.out.println();
            }
        }

    }

    private static void deleteUser() {
        System.out.println("delete user");
    }

    private static void editUser() {
        System.out.println("edit user");
    }

    private static void addUser() {
        System.out.println("add user");
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("Choose option and press enter. Options: ");
        System.out.println("1 - Add new user");
        System.out.println("2 - edit user");
        System.out.println("3 - delete user");
        System.out.println("0 - quit");

    }
}
