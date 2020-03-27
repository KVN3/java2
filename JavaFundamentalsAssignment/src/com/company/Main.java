package com.company;

import database.Database;
import database.models.Account;

import java.text.MessageFormat;

public class Main {

    public static void main(String[] args) {
        ActionHandler actionHandler = new ActionHandler(new Database());

        Account account = actionHandler.authorize();

        System.out.println(MessageFormat.format("\nWelcome, {0}! Your access level is: {1} \n",
                account.username, account.accessLevel));

        ActionResult actionResult = ActionResult.CONTINUE;

        while (actionResult != ActionResult.EXIT) {
            Action action = actionHandler.requestAction(account);
            actionResult = actionHandler.executeAction(action);
        }
    }


}
