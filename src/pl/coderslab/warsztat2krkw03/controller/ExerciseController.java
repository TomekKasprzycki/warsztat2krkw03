package pl.coderslab.warsztat2krkw03.controller;

import pl.coderslab.warsztat2krkw03.dao.ExerciseDAO;
import pl.coderslab.warsztat2krkw03.model.Exercise;

import java.util.Scanner;

public class ExerciseController {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to exercise manager!");

        while (true){
            displayMenu();
            final String option = scan.nextLine();

            if (option.equals("1")) {
                addExercise();
            } else if (option.equals("2")) {
                editExercise();
            } else if (option.equals("3")) {
                deleteExercise();
            } else if (option.equals("0")) {
                System.out.println("Program has been closed.");
                break;
            } else {
                System.out.println();
            }
        }


    }

    private static void deleteExercise() {
        System.out.println();
        System.out.println("Exercise deletion.");
        System.out.println("------------------");
        System.out.print("Enter exercise id: ");
        Scanner scanner = new Scanner(System.in);
        String idStr = scanner.nextLine();
        int id = Integer.parseInt(idStr);

        while (true) {
            System.out.println("Are You sure? y/n:");
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                ExerciseDAO.deleteExercise(id);
                System.out.println("Exercise has been deleted.");
                break;
            } else if (answer.equals("n")) {
                System.out.println("Deletion has been aborted!");
                break;
            } else {
                System.out.println("Wrong option. Try again!");
            }
        }
    }

    private static void editExercise() {
        Scanner scan=new Scanner(System.in);

        System.out.println();
        System.out.println("Exercise edition.");
        System.out.println("-----------------");
        System.out.print("Enter exercise id: ");
        String idStr = scan.nextLine();
        int id=Integer.parseInt(idStr);

        String[] exerciseData=ExerciseDAO.selectExercise(id).split(",");

        System.out.println("Set new data or leave blank if You don't want to change.");
        System.out.print("Enter new title: ");
        String title=scan.nextLine();
        if (title.equals("")) {
            title = exerciseData[0];
        }

        System.out.print("Enter new description: ");
        String description=scan.nextLine();
        if (description.equals("")){
            description=exerciseData[1];
        }

        if (ExerciseDAO.editExercise(id,title,description)) {
            System.out.println("Data has been updated!");
        } else {
            System.out.println("Something went wrong and data has not been updated!");
        }

    }

    private static void addExercise() {
        Scanner scan=new Scanner(System.in);
        String title ="";
        String description="";

        System.out.println();
        System.out.println("Add new exercise.");
        while (true) {
            System.out.print("Enter title: ");
            title = scan.nextLine();
            if (title.equals("")){
                System.out.println("Title cannot be empty!");
            } else {
                break;
            }
        }

        while (true){
            System.out.print("Enter exercise description: ");
            description=scan.nextLine();
            if (description.equals("")){
                System.out.println("Description cannot be empty!");
            } else {
                break;
            }
        }

        Exercise exercise = new Exercise(title, description);

        ExerciseDAO.createExercise(exercise);
        System.out.println("Exercise was added, id="+exercise.getId());

    }


    private static void displayMenu() {
        System.out.println();
        System.out.println("Choose option and press enter. Options: ");
        System.out.println("1 - Add new exercise");
        System.out.println("2 - edit exercise");
        System.out.println("3 - delete exercise");
        System.out.println("0 - quit");


    }

}
