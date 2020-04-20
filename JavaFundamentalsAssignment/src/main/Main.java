package main;

import database.Database;
import database.models.AccessLevel;
import database.models.Account;
import main.handlers.AuthorizationHandler;
import main.handlers.BasicHandler;
import main.handlers.EditorHandler;
import main.handlers.models.Action;
import main.handlers.ActionHandler;
import main.handlers.models.ActionResult;

import java.text.MessageFormat;

public class Main {

    public static void main(String[] args) {
        Database db = new Database();

        // Auth
        AuthorizationHandler authorizationHandler = new AuthorizationHandler(db);
        Account account = authorizationHandler.authorize();
        System.out.println(MessageFormat.format("\nWelcome, {0}! Your access level is: {1} \n",
                account.getUsername(), account.getAccessLevel()));

        // Program
        ActionHandler actionHandler = new ActionHandler(db, account.getAccessLevel());
        ActionResult actionResult = ActionResult.CONTINUE;

        while (actionResult != ActionResult.EXIT) {
            Action action = actionHandler.requestAction(account);
            actionResult = actionHandler.executeAction(actionHandler, action);

            while (actionResult == ActionResult.CONTINUE_REPORTS) {
                actionResult = actionHandler.handleStudentReport();
            }
        }
    }
}
