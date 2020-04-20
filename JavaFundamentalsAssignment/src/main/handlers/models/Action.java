package main.handlers.models;

import database.models.AccessLevel;

public class Action {
    public AccessLevel accessLevel;
    public char key;
    public String optionName;

    public Action(AccessLevel accessLevel, char key, String optionName) {
        this.accessLevel = accessLevel;
        this.key = key;
        this.optionName = optionName;
    }
}
