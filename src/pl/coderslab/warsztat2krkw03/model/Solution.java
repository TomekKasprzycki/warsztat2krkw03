package pl.coderslab.warsztat2krkw03.model;

import java.sql.SQLData;
import java.util.Date;

public class Solution {

    private int id;
    private Date created;
    private Date updated;
    private String description;
    private int exercseID;
    private int userID;

    public Solution(int id, Date created, Date updated, String description, int exercseID, int userID) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercseID = exercseID;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getDescription() {
        return description;
    }

    public int getExercseID() {
        return exercseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExercseID(int exercseID) {
        this.exercseID = exercseID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
