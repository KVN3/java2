package main.handlers;

import database.Database;
import database.models.AccessLevel;
import database.models.Account;
import database.models.Report;
import main.handlers.models.Action;
import main.handlers.models.ActionResult;

import java.util.ArrayList;
import java.util.Scanner;

public class ActionHandler {

    protected Database database;
    protected Scanner in;
    protected ArrayList<Action> actions;
    protected AccessLevel accessLevel;
    private BasicHandler basicHandler;
    private EditorHandler editorHandler;
    private AdminHandler adminHandler;

    public ActionHandler(Database database, AccessLevel accessLevel) {
        this.database = database;
        this.in = new Scanner(System.in);
        this.accessLevel = accessLevel;
        this.basicHandler = new BasicHandler(database);
        this.editorHandler = new EditorHandler(database);
        this.adminHandler = new AdminHandler(database);

        SetActions();
    }

    public void SetActions() {
        this.actions = new ArrayList<Action>();
        this.actions.add(new Action(AccessLevel.BASIC, 'S', "Display Students"));
        this.actions.add(new Action(AccessLevel.BASIC, 'T', "Display Teachers"));
        this.actions.add(new Action(AccessLevel.EDITOR, 'A', "Add Students"));
        this.actions.add(new Action(AccessLevel.EDITOR, 'R', "Display Reports"));
        this.actions.add(new Action(AccessLevel.ADMIN, 'V', "Save Reports"));
        this.actions.add(new Action(AccessLevel.BASIC, 'X', "Exit"));
    }

    public Action requestAction(Account account) {
        ArrayList<Action> allowedActions = getAllowedActions(account.getAccessLevel());

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

    public ActionResult handleStudentReport() {
        System.out.print("Enter student id (Report Details) | Or B back to main menu: ");
        String studentIdInput = in.next();
        System.out.println();

        // Back to main
        if (studentIdInput.toUpperCase().equals("B"))
            return ActionResult.CONTINUE;

        try {

            // Get a specific report and show it
            int studentId = Integer.parseInt(studentIdInput);
            Report report = database.getReport(studentId);

            // Invalid id, no report found
            if (report == null) {
                System.out.println("No report found for given ID.");
                return handleStudentReport();
            }

            System.out.println(report.getPrintInfo());

            // Handles the actions related to a single report view
            return handleReportAction(studentId);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input, try again.");
            return handleStudentReport();
        }
    }

    private ActionResult handleReportAction(int studentId) {
        System.out.println("A. Add (Update) Report | R. Display Reports | B. Back to Main | X. Exit |");
        System.out.println();
        System.out.print("Please, enter a choice: ");
        String input = in.next();
        System.out.print("\n");

        try {
            char inputKey = input.toUpperCase().charAt(0);

            switch (inputKey) {
                case 'A':
                    editorHandler.updateReport(studentId);
                    break;
                case 'R':
                    System.out.println();
                    editorHandler.displayReports();
                    break;
                case 'B':
                    System.out.println("Returning to main.");
                    return ActionResult.CONTINUE;
                case 'X':
                    return ActionResult.EXIT;
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong... returning to base.");
            return ActionResult.CONTINUE;
        }

        return ActionResult.CONTINUE_REPORTS;
    }

    // Handles the execution of a main menu action
    public ActionResult executeAction(ActionHandler actionHandler, Action action) {
        ActionResult actionResult = ActionResult.CONTINUE;

        switch (action.key) {
            case 'S':
                basicHandler.displayStudents();
                break;
            case 'T':
                basicHandler.displayTeachers();
                break;
            case 'X':
                actionResult = basicHandler.exitProgram();
                break;
            case 'A':
                if (accessLevel.getValue() >= AccessLevel.EDITOR.getValue())
                    editorHandler.AddStudent();
                break;
            case 'R':
                if (accessLevel.getValue() >= AccessLevel.EDITOR.getValue())
                    actionResult = editorHandler.displayReports();
                break;
            case 'V':
                if (accessLevel.getValue() >= AccessLevel.ADMIN.getValue())
                    adminHandler.saveReportsToFileSystem(database.getReports());
                break;
        }

        System.out.print("\n\n");
        return actionResult;
    }
}
