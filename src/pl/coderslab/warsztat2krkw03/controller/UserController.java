package pl.coderslab.warsztat2krkw03.controller;

import pl.coderslab.warsztat2krkw03.dao.UserDao;
import pl.coderslab.warsztat2krkw03.model.User;

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
            } else if (option.equals("3")1){
                deleteUser();
            } else if (option.equals("0")){
                System.out.println("Zakończono działanie programu. Do zobacznia!");
                break;
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
        Scanner scan = new Scanner(System.in);
        System.out.println("Add user:");
        System.out.print("Enter user name: ");
        final String username = scan.nextLine();
        System.out.print("Enter user email: ");
        final String email = scan.nextLine();
        System.out.print("Enter user password: ");
        final String password = scan.nextLine();

        User user = new User(username,email,password);
        UserDao.create(user);
        System.out.println("User was added, id="+user.getId());

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
