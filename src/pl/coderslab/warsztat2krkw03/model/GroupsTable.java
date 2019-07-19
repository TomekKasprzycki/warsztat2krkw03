package pl.coderslab.warsztat2krkw03.model;

public class GroupsTable {

    private int id;
    private String name;

    public GroupsTable() {
    }

    public GroupsTable(String name) { this.name = name; }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

}
