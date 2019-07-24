package pl.coderslab.warsztat2krkw03.dao;

import pl.coderslab.warsztat2krkw03.db.db;
import pl.coderslab.warsztat2krkw03.model.User;
import java.sql.*;

public class UserDAO {


    private static final String qryCreateUser = "INSERT INTO users(username, email, groupID, password) VALUES (?,?,?,?)";
    private static final String qryEditUser = "UPDATE users SET userName=?, email=?, password=?, groupID=? WHERE id=?";
    private static final String qryDeleteUser = "DELETE FROM users WHERE id=?";
    private static final String qrySelectUser = "SELECT username, email, groupID, password FROM users WHERE id=?";

    public static User create(User user) {

        try (Connection connection =
                     DriverManager.getConnection(db.URL, db.USER, db.PASSWORD)
        ) {

            PreparedStatement ps = connection.prepareStatement(qryCreateUser, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setInt(3,user.getGroupID());
            ps.setString(4, user.getPassword());

            ps.executeUpdate();

            final ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static boolean editUser(int id, String userName, String email, String password, int groupID) {
        boolean result = false;

        try (Connection connection = DriverManager.getConnection(db.URL, db.USER, db.PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(qryEditUser);
            statement.setString(1, userName);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setInt(4, groupID);
            statement.setInt(5, id);

            statement.executeUpdate();
            result = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String selectUser(int id) {
        ResultSet rs = null;
        String userData ="";

        try (Connection connection = DriverManager.getConnection(db.URL, db.USER, db.PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(qrySelectUser);
            statement.setInt(1, id);

            rs = statement.executeQuery();
            rs.next();
            userData = rs.getString(1) + "," + rs.getString(2) +
                    "," + rs.getInt(3) + "," + rs.getString(4);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userData;
    }

    public static void deleteUser(int id){

        try (Connection connection=DriverManager.getConnection(db.URL,db.USER, db.PASSWORD)){

            PreparedStatement statement = connection.prepareStatement(qryDeleteUser);
            statement.setInt(1,id);

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}