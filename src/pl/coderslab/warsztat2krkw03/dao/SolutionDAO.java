package pl.coderslab.warsztat2krkw03.dao;

import pl.coderslab.warsztat2krkw03.db.db;
import pl.coderslab.warsztat2krkw03.model.Solution;

import java.sql.*;

public class SolutionDAO {

    private static String qryAddSolution = "INSERT INTO solution (created, updated, description, exerciseID, userID) " +
            "VALUES (?,?,?,?,?)";
    private static String qryEditSolution = "UPDATE solution SET updated=?, description=?, exerciseID=?, userID=? " +
            "WHERE id=?";
    private static String qryDeleteSolution = "DELETE FROM solution WHERE id=?";
    private static String qrySelectSolutiona = "SELECT id, description, exerciseID, userID " +
            "FROM solution WHERE id=?";

    public static Solution addSolution(Solution solution){

        try(Connection connection= DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)){

            PreparedStatement preparedStatement = connection.prepareStatement(qryAddSolution, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setDate(1, (Date) solution.getCreated());
            preparedStatement.setDate(2,(Date) solution.getUpdated());
            preparedStatement.setString(3,solution.getDescription());
            preparedStatement.setInt(4,solution.getExercseID());
            preparedStatement.setInt(5,solution.getUserID());

            preparedStatement.executeUpdate();

            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }


        } catch (SQLException e){
            e.printStackTrace();
        }

        return solution;
    }

    public static boolean editSolution (int id, java.util.Date updated, String description, int exerciseID, int userID){
        boolean result=false;

        try (Connection connection=DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)){

            PreparedStatement preparedStatement = connection.prepareStatement(qryEditSolution);

            preparedStatement.setDate(1,(Date) updated);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,exerciseID);
            preparedStatement.setInt(4,userID);
            preparedStatement.setInt(5,id);

            preparedStatement.executeUpdate();

            result = true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public static String selectSolution (int id){
        String result="";

        try (Connection connection=DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)){

            PreparedStatement preparedStatement = connection.prepareStatement(qrySelectSolutiona);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            result = resultSet.getInt(1) + "," + resultSet.getString(4) + "," +
                    resultSet.getInt(5) + "," + resultSet.getInt(6);


        }catch (SQLException e){
            e.printStackTrace();
        }


        return result;
    }

    public static boolean deleteSolution (int id){
        boolean result=false;

        try (Connection connection=DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)){

            PreparedStatement preparedStatement = connection.prepareStatement(qryDeleteSolution);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();

            result = true;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }


}
