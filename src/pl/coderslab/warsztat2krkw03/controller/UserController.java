package pl.coderslab.warsztat2krkw03.controller;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.warsztat2krkw03.dao.UserDAO;
import pl.coderslab.warsztat2krkw03.model.User;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {

    public static void main(String[] args) {

        System.out.println("Welcome to user manager!");
        Scanner scan = new Scanner(System.in);

        while(true){
            displayMenu();
            final String option = scan.nextLine();

            if (option.equals("1")){
                addUser();
            } else if (option.equals("2")){
                try {
                    editUser();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if (option.equals("3")){
                deleteUser();
            } else if (option.equals("0")){
                System.out.println("Program has been closed.!");
                break;
            } else {
                System.out.println();
            }
        }
    }

    private static void deleteUser() {
        System.out.println();
        System.out.println("User deletion.");
        System.out.println("--------------");
        System.out.print("Enter user id: ");
        Scanner scanner = new Scanner(System.in);
        String idStr = scanner.nextLine();
        int id = Integer.parseInt(idStr);

        while (true) {
            System.out.println("Are You sure? y/n:");
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                UserDAO.deleteUser(id);
                System.out.println("User has been deleted.");
                break;
            } else if (answer.equals("n")) {
                System.out.println("Deletion has been aborted!");
                break;
            } else {
                System.out.println("Wrong option. Try again!");
            }
        }
    }

    private static void editUser() throws SQLException {
        Scanner scan = new Scanner(System.in);
        String userName="";
        String email="";
        String password="";
        int groupID=0;

        System.out.println("User data edition.");
        System.out.println("------------------");
        System.out.print("Enter id user You want to edit: ");
        String idStr = scan.nextLine();
        int id=Integer.parseInt(idStr);
        String[] userData = UserDAO.selectUser(id).split(",");

        System.out.println("Set new data or leave blank if You don't want to change.");
        System.out.print("Enter new user name: ");
        userName = scan.nextLine();
        if (userName.equals("")){
            userName=userData[0];
        }
        System.out.print("Enter new email: ");
        email=scan.nextLine();
        if (email.equals("")){
            email=userData[1];
        }

        System.out.print("Enter new password: ");
        String unchangedPassword = scan.nextLine();
        if (unchangedPassword.equals("")){
            password=userData[3];
        } else {
            password= BCrypt.hashpw(unchangedPassword, BCrypt.gensalt());
        }

        System.out.print("Enter new group (id): ");
        String groupIDStr=scan.nextLine();
        if (groupIDStr.equals("")){
            int grID = Integer.parseInt(userData[2]);
            groupID = grID;
        }else{
            groupID = Integer.parseInt(groupIDStr);
        }

        if (UserDAO.editUser(id,userName,email,password,groupID)){
            System.out.println("Data has been changed!");
        }else {
            System.out.println("Something went wrong and data has been not changed!");
        }
    }


    private static void addUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Adding user.");
        System.out.print("Enter user name: ");
        final String username = scan.nextLine();
        System.out.print("Enter user email: ");
        final String email = scan.nextLine();
        System.out.print("Enter user password: ");
        final String password = scan.nextLine();
        System.out.print("Enter groupID: ");
        final String grIDStr = scan.nextLine();
        final int groupID = Integer.parseInt(grIDStr);

        User user = new User(username,email,groupID,password);
        UserDAO.create(user);
        System.out.println("User was added, id="+user.getId());

    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("Choose option and press enter.");
        System.out.println("Options:");
        System.out.println("1 - Add new user");
        System.out.println("2 - Edit user");
        System.out.println("3 - Delete user");
        System.out.println("0 - Quit");
        System.out.print("Your choice: ");

    }
}
