package main.handlers;

import database.Database;
import database.models.Account;

public class AuthorizationHandler extends BaseHandler {

    public AuthorizationHandler(Database database) {
        super(database);
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
}
