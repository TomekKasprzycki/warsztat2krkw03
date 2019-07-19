package pl.coderslab.warsztat2krkw03.dao;

import pl.coderslab.warsztat2krkw03.db.db;
import pl.coderslab.warsztat2krkw03.model.GroupsTable;

import java.sql.*;

public class GroupsTableDAO {

    final static String qryInsertIntoGroupsTable = "INSERT INTO groupsTable (name) VALUES (?)";
    final static String qryDeleteGroup = "DELETE FROM groupsTable WHERE id=?";
    final static String qryEditGroup = "UPDATE groupsTable SET name=? WHERE id=?";

    public static GroupsTable createGroup(GroupsTable groupsTable){
        try (Connection connection = DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)) {

            PreparedStatement statement = connection.prepareStatement(qryInsertIntoGroupsTable, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,groupsTable.getName());

            statement.executeUpdate();

            final ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
                groupsTable.setId(generatedKeys.getInt(1));
            }

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

    public static void editGroup(int id, String name){

        try (Connection connection=DriverManager.getConnection(db.URL, db.USER, db.PASSWORD)){

            PreparedStatement statement = connection.prepareStatement(qryEditGroup);
            statement.setString(1,name);
            statement.setInt(2,id);

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
