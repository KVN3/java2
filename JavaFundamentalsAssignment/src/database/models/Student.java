package database.models;

import java.time.LocalDate;

public class Student extends Person {
    protected Group group;

    public Student(int id, Account account, String forename, String surname, LocalDate dateOfBirth, Group group) {
        super(id, account, forename, surname, dateOfBirth);
        this.group = group;
    }

    public Student(Account account, String forename, String surname, LocalDate dateOfBirth, Group group) {
        super(account, forename, surname, dateOfBirth);
        this.group = group;
    }

    public void printAsRow()
    {
        System.out.format("%-6d%-13s%-12s%-13s%-7d%-10s", id, forename, surname, dateOfBirth, getAge(), group.groupName);
        System.out.println();
    }
}
