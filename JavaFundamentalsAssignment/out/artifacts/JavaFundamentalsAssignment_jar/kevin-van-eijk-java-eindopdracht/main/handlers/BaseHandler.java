package main.handlers;

import database.Database;
import main.handlers.models.Action;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class BaseHandler {
    protected Database database;
    protected Scanner in;

    public BaseHandler(Database database) {
        this.database = database;
        this.in = new Scanner(System.in);
    }
}
