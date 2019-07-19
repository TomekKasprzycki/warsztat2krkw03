package pl.coderslab.warsztat2krkw03.controller;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.warsztat2krkw03.dao.UserDAO;
import pl.coderslab.warsztat2krkw03.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                try {
                    editUser();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if (option.equals("3")){
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

    private static void editUser() throws SQLException {
        Scanner scan = new Scanner(System.in);
        ResultSet rs;
        String userName="";
        String email="";
        String password="";
        int groupID=0;

        System.out.println("User data edition.");
        System.out.println("------------------");
        System.out.print("Enter id user You want to edit: ");
        int id=scan.nextInt();

        rs=UserDAO.selectUser(id);
        System.out.println(rs.toString());

        System.out.println("Set new data or leave blank if You don't want to change.");
        System.out.print("Enter new user name: ");
        userName = scan.nextLine();
        if (userName.equals("")){
            userName=rs.getString(2);
        }
        System.out.println("Enter new email: ");
        email=scan.nextLine();
        if (email.equals("")){
            email=rs.getString(3);
        }

        System.out.println("Enter new password: ");
        String unchangedPassword = scan.nextLine();
        if (unchangedPassword.equals("")){
            password=rs.getString(4);
        } else {
            password= BCrypt.hashpw(unchangedPassword, BCrypt.gensalt());
        }

        System.out.println("Enter new group (id): ");
        String groupIDStr=scan.nextLine();
        if (groupIDStr.equals("")){
            groupID = rs.getInt(5);
        }else{
            groupID = Integer.parseInt(groupIDStr);
        }

        if (UserDAO.editUser(id,userName,email,password,groupID)){
            System.out.println("Data has been changed!");
        }else {
            System.out.println("Something went wrong and data are not changed!");
        }




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
        UserDAO.create(user);
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
