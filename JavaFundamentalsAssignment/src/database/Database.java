package database;

import database.models.Account;

import java.util.ArrayList;

public class Database {
    private ArrayList<Account> accounts;

    public Database() {
        init();
    }

    public void init() {
        accounts = new ArrayList<Account>();
        accounts.add(new Account("emma", "emma12"));
        accounts.add(new Account("1", "1"));

    }

    public Account getAccount(String username, String password){
        for(Account account : accounts){
            if(account.username.trim().equals(username.trim()) && account.password.trim().equals(password.trim()))
                return account;
        }

        return null;
    }
}
