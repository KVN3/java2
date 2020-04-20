package database.models;

public class Account {
    private String username;
    private String password;
    private AccessLevel accessLevel;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public Account(String username, String password) {
        this(username, password, AccessLevel.BASIC);
    }

    public Account(String username, String password, AccessLevel accessLevel) {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }
}


