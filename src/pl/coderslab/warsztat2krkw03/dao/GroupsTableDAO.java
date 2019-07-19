package pl.coderslab.warsztat2krkw03.dao;

import pl.coderslab.warsztat2krkw03.db.db;
import pl.coderslab.warsztat2krkw03.model.GroupsTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupsTableDAO {

    final static String qryInsertIntoGroupsTable = "INSERT INTO groupsTable (name) VALUES (?)";
    final static String qryDeleteGroup = "DELETE FROM groupsTable WHERE id=?";

    public static GroupsTable createGroup(GroupsTable groupsTable){
        try (Connection connection = DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)) {

            PreparedStatement statement = connection.prepareStatement(qryInsertIntoGroupsTable);
            statement.setString(1,groupsTable.getName());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }


        return groupsTable;
    }

    public static void deleteGroup(int id) {

        try (Connection connection = DriverManager.getConnection(db.URL, db.USER, db.PASSWORD)) {

            PreparedStatement statement = connection.prepareStatement(qryDeleteGroup);
            statement.setInt(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
