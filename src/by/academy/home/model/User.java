package by.academy.home.model;

public class User {

    private final int id;
    private final String name;
    private final String password;
    private final String access;

    public User(int id, String name, String password, String access) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.access = access;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccess() {
        return access;
    }

    @Override
    public String toString() {
        return id + "/" + name + "/" + password + "/" + access;
    }
}
