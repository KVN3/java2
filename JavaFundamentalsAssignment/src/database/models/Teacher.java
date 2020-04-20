package database.models;

import java.time.LocalDate;

public class Teacher extends Person {
    protected double salary;

    public Teacher(int id, Account account, String forename, String surname, LocalDate dateOfBirth, double salary) {
        super(id, account, forename, surname, dateOfBirth);
        this.salary = salary;
    }

    public Teacher(Account account, String forename, String surname, LocalDate dateOfBirth, double salary) {
        super(account, forename, surname, dateOfBirth);
        this.salary = salary;
    }

    public void printAsRow()
    {
        System.out.format("%-6d%-13s%-12s%-13s%-7d%-1s%-10.2f", id, forename, surname, dateOfBirth, getAge(), "€", salary);
        System.out.println();
    }
}
