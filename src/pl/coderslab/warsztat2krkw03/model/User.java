package pl.coderslab.warsztat2krkw03.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private int groupID;

    public User() {
    }

    public User(String username, String email, int groupID, String password) {
        this.username = username;
        this.email = email;
        this.groupID = groupID;
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public String getUsername() { return username; }

    public String getEmail() {
        return email;
    }

    public int getGroupID() { return groupID; }

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

    public void setGroupID() {this.groupID=groupID; }

    public boolean isPasswordCorrect(String password){

        return BCrypt.checkpw(password, getPassword());
    }

}
