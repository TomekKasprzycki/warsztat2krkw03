package pl.coderslab.warsztat2krkw03.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.warsztat2krkw03.db.db;
import pl.coderslab.warsztat2krkw03.model.User;

import java.sql.*;

public class UserDAO {


    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
    private static final String qryEditUser = "UPDATE users SET userName=?, email=?, password=?, groupID=? WHERE id=?";
    private static final String qryDeleteUser = "DELETE FROM users WHERE id=?";
    private static final String qrySelectUser = "SELECT id, username, email, password, groupID FROM users WHERE id=?";

    public static User create(User user) {

        try (Connection connection =
                     DriverManager.getConnection(db.URL, db.USER, db.PASSWORD)
        ) {

            PreparedStatement ps = connection.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

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

    public static ResultSet selectUser(int id) throws SQLException {
        ResultSet rs = null;

        try (Connection connection = DriverManager.getConnection(db.URL, db.USER, db.PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(qrySelectUser);
            statement.setInt(1, id);

            rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}