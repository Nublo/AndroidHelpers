package by.anatoldeveloper.helpers.testframeworks.activity.model;

public class User {

    private final String id;
    private final String userFirstName;
    private final String userSecondName;

    public User(String id, String userFirstName, String userSecondName) {
        this.id = id;
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
    }

    public String getId() {
        return id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

}