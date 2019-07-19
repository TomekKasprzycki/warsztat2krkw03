package pl.coderslab.warsztat2krkw03.controller;

import pl.coderslab.warsztat2krkw03.dao.GroupsTableDAO;
import pl.coderslab.warsztat2krkw03.model.GroupsTable;

import java.util.Scanner;

public class GroupsController {

    public static void main(String[] args) {

        System.out.println("Welcome to groups manager!");


        Scanner scan = new Scanner(System.in);

        while (true) {
            displayMenu();
            final String option = scan.nextLine();

            if (option.equals("1")) {
                addGroup();
            } else if (option.equals("2")) {
                editGroup();
            } else if (option.equals("3")) {
                deleteGroup();
            } else if (option.equals("0")) {
                System.out.println("Program has been closed.");
                break;
            } else {
                System.out.println();
            }

        }
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("Choose option and press enter. Options: ");
        System.out.println("1 - Add new group");
        System.out.println("2 - edit group");
        System.out.println("3 - delete group");
        System.out.println("0 - quit");


    }

    private static void deleteGroup() {
        System.out.println();
        System.out.println("Group delete menu.");
        System.out.println("------------------");
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("Insert group id which You want to delete: ");
        String groupIDStr = scan.nextLine();
        int groupID = Integer.parseInt(groupIDStr);

        while (true) {
            System.out.println("Are You sure? y/n:");
            String answer = scan.nextLine();
            if (answer.equals("y")) {
                GroupsTableDAO.deleteGroup(groupID);
                System.out.println("Group has been deleted.");
                break;
            } else if (answer.equals("n")) {
                System.out.println("Deletion has been aborted!");
                break;
            } else {
                System.out.println("Wrong option. Try again!");
            }
        }
    }

    private static void editGroup() {
        System.out.println();
        System.out.println("Group edition menu.");
        System.out.println("------------------");
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("Insert group id which You want to edit: ");
        String groupIDStr = scan.nextLine();
        int groupID = Integer.parseInt(groupIDStr);
        System.out.println();
        System.out.print("Set new name for this group: ");
        String newName = scan.nextLine();

        GroupsTableDAO.editGroup(groupID,newName);

        System.out.println("Group name has been changed.");
    }

    private static void addGroup() {

        System.out.println();
        System.out.println("New group creator menu.");
        System.out.println("------------------");
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("Insert group name: ");
        String groupName = scan.nextLine();
        GroupsTable group = new GroupsTable(groupName);
        GroupsTableDAO.createGroup(group);
        System.out.println("Group " + group.getName() +" has been created. ID this group is " + group.getId());

    }

}