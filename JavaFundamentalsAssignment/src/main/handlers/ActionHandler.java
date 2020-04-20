package com.company;

import database.Database;
import database.models.AccessLevel;
import database.models.Account;
import database.models.Student;
import database.models.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class ActionHandler {

    private Database database;
    private Scanner in;
    private ArrayList<Action> actions;

    public ActionHandler(Database database) {
        this.database = database;
        this.in = new Scanner(System.in);

        init();
    }

    public void init() {
        this.actions = new ArrayList<Action>();

        this.actions.add(new Action(AccessLevel.BASIC, 'S', "Display Students"));
        this.actions.add(new Action(AccessLevel.BASIC, 'T', "Display Teachers"));
        this.actions.add(new Action(AccessLevel.BASIC, 'X', "Exit"));
    }

    public Account authorize() {
        System.out.print("Enter username: ");
        String usernameInput = in.next();
        System.out.print("Enter password: ");
        String passwordInput = in.next();

        Account account = database.getAccount(usernameInput, passwordInput);
        if (account != null)
            return account;
        else {
            System.out.println("\nAuthorization failed, please try again...");
            return authorize();
        }
    }

    public Action requestAction(Account account) {
        ArrayList<Action> allowedActions = getAllowedActions(account.accessLevel);

        // Present options and process user input
        printOptions(allowedActions);
        Action requestedAction = getRequestedAction(allowedActions);

        // Action found for given input? Return the action
        if (requestedAction != null)
            return requestedAction;

        // Ask again if no action was found for the given input
        return requestAction(account);
    }

    // Filters the total action list based on accessLevel
    private ArrayList<Action> getAllowedActions(AccessLevel accessLevel) {
        ArrayList<Action> possibleActions = new ArrayList<Action>();

        for (Action action : actions) {
            if (action.accessLevel.getValue() <= accessLevel.getValue()) {
                possibleActions.add(action);
            }
        }

        return possibleActions;
    }

    private void printOptions(ArrayList<Action> allowedActions) {
        String optionsText = "";
        for (Action action : allowedActions) {
            optionsText += action.key + ". " + action.optionName + " | ";
        }

        System.out.print(optionsText);
        System.out.print("\n\n");
    }

    private Action getRequestedAction(ArrayList<Action> allowedActions) {
        System.out.print("Please, enter a choice: ");
        char inputKey = in.next().charAt(0);
        System.out.print("\n\n");

        for (Action action : allowedActions) {
            if (action.key == Character.toUpperCase(inputKey))
                return action;
        }

        return null;
    }

    public ActionResult executeAction(Action action) {
        ActionResult actionResult = ActionResult.CONTINUE;

        switch (action.key) {
            case 'S':
                displayStudents();
                break;
            case 'T':
                displayTeachers();
                break;
            case 'X':
                actionResult = exitProgram();
                break;
        }

        System.out.print("\n\n");
        return actionResult;
    }

    private ActionResult displayTeachers() {
        String formattingString = "%-6s%-13s%-12s%-13s%-7s%-11s";

        System.out.println("LIST OF TEACHERS");
        System.out.format(formattingString, "Id", "FirstName", "LastName", "BirthDate", "Age", "Salary");
        System.out.println();
        System.out.format(formattingString, "**", "*********", "********", "*********", "***", "******");
        System.out.println();

        for (Teacher teacher : database.getTeachers()) { teacher.printInfo(); }

        return ActionResult.CONTINUE;
    }

    private ActionResult exitProgram() {
        System.out.print("Exiting program...");

        return ActionResult.EXIT;
    }

    private ActionResult displayStudents() {
        String formattingString = "%-6s%-13s%-12s%-13s%-7s%-10s";

        System.out.println("LIST OF STUDENTS");
        System.out.format(formattingString, "Id", "FirstName", "LastName", "BirthDate", "Age", "Group");
        System.out.println();
        System.out.format(formattingString, "**", "*********", "********", "*********", "***", "*****");
        System.out.println();

        for (Student student : database.getStudents()) { student.printInfo(); }

        return ActionResult.CONTINUE;
    }
}
