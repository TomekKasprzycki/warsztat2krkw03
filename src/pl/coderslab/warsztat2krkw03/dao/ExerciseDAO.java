package pl.coderslab.warsztat2krkw03.dao;

import pl.coderslab.warsztat2krkw03.db.db;
import pl.coderslab.warsztat2krkw03.model.Exercise;
import java.sql.*;

public class ExerciseDAO {

    private static String qryCreateExercise = "INSERT INTO exercise (title, description) VALUES (?,?)";
    private static String qryEditExercise = "UPDATE exercise SET title=?, description=? WHERE id=?";
    private static String qryDeleteExercise = "DELETE FROM exercise WHERE id=?";
    private static String qrySelectExercise = "SELECT title, description FROM exercise WHERE id=?";

    public static Exercise createExercise(Exercise exercise){

        try(Connection connection=DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)){
            PreparedStatement preparedStatement=connection.prepareStatement(qryCreateExercise, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,exercise.getTitle());
            preparedStatement.setString(2,exercise.getDescription());

            preparedStatement.executeUpdate();

            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                exercise.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return exercise;
    }

    public static boolean editExercise(int id, String title, String description){
        boolean result=false;

        try (Connection connection=DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)){
            PreparedStatement preparedStatement=connection.prepareStatement(qryEditExercise);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,id);

            preparedStatement.executeUpdate();
            result=true;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public static boolean deleteExercise(int id){
        boolean result=false;

        try (Connection connection=DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)){
            PreparedStatement preparedStatement=connection.prepareStatement(qryDeleteExercise);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            result=true;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public static String selectExercise(int id){
        ResultSet rs=null;
        String exerciseData="";

        try (Connection connection=DriverManager.getConnection(db.URL,db.USER,db.PASSWORD)){
            PreparedStatement preparedStatement=connection.prepareStatement(qrySelectExercise);
            preparedStatement.setInt(1, id);

            rs=preparedStatement.executeQuery();
            rs.next();

            exerciseData = rs.getString(1) + "," + rs.getString(2);



        } catch (SQLException e){
            e.printStackTrace();
        }

        return exerciseData;
    }

}
