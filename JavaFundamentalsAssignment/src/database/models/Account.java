package database.models;

public class Account {
    public String username;
    public String password;
    public AccessLevel accessLevel;

    public Account(String username, String password) {
        this(username, password, AccessLevel.BASIC);
    }

    public Account(String username, String password, AccessLevel accessLevel) {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }
}


