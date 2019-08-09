package pl.coderslab.warsztat2krkw03.controller;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.warsztat2krkw03.dao.SolutionDAO;
import pl.coderslab.warsztat2krkw03.dao.UserDAO;
import pl.coderslab.warsztat2krkw03.model.Solution;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class SolutionController {

    public static void main(String[] args) {

        System.out.println("Welcome to solution manager!");
        Scanner scan = new Scanner(System.in);

        while(true){
            displayMenu();
            final String option = scan.nextLine();

            if (option.equals("1")){
                addSolution();
            } else if (option.equals("2")){
                try {
                    editSolution();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else if (option.equals("3")){
                deleteSolution();
            } else if (option.equals("0")){
                System.out.println("Program has been closed.!");
                break;
            } else {
                System.out.println();
            }
        }
    }

    private static void deleteSolution() {
        System.out.println();
        System.out.println("Solution deletion.");
        System.out.println("--------------");
        System.out.print("Enter solution id: ");
        Scanner scanner = new Scanner(System.in);
        String idStr = scanner.nextLine();
        int id = Integer.parseInt(idStr);

        while (true) {
            System.out.println("Are You sure? y/n:");
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                SolutionDAO.deleteSolution(id);
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

    private static void editSolution() throws SQLException {
        Scanner scan = new Scanner(System.in);
        int id=0;
        int exerciseID=0;
        int userID=0;
        String description="";
        Date updated= new Date();

        System.out.println("Solution data edition.");
        System.out.println("------------------");
        System.out.print("Enter id solution You want to edit: ");
        String idStr = scan.nextLine();
        id=Integer.parseInt(idStr);
        String[] solutionData = SolutionDAO.selectSolution(id).split(",");

        System.out.println("Set new data or leave blank if You don't want to change.");
        System.out.print("Enter new solution description: ");
        description = scan.nextLine();
        if (description.equals("")){
            description=solutionData[0];
        }
        System.out.print("Enter new exerciseID: ");
        String exerciseIDstr=scan.nextLine();
        if (exerciseIDstr.equals("")){
            exerciseID= Integer.parseInt(solutionData[1]);
        } else {
            exerciseID=Integer.parseInt(exerciseIDstr);
        }

//         userID
// dokończyć


        if (SolutionDAO.editSolution(id,updated,description,exerciseID,userID)){
            System.out.println("Data has been changed!");
        }else {
            System.out.println("Something went wrong and data has been not changed!");
        }


    }

    private static void addSolution() {

    }


    private static void displayMenu() {
        System.out.println();
        System.out.println("Choose option and press enter.");
        System.out.println("Options:");
        System.out.println("1 - Add new solution");
        System.out.println("2 - Edit solution");
        System.out.println("3 - Delete solutin");
        System.out.println("0 - Quit");
        System.out.print("Your choice: ");

    }
}
