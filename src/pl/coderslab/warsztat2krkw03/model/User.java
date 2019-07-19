package pl.coderslab.warsztat2krkw03.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
//    id int AUTO_INCREMENT,
//    username VARCHAR(255) UNIQUE NOT NULL,
//    email VARCHAR(255) UNIQUE NOT NULL,
//    password VARCHAR (255) NOT NULL,

    private int id;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public String getUsername() { return username; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isPasswordCorrect(String password){

        return BCrypt.checkpw(password, getPassword());
    }

}
