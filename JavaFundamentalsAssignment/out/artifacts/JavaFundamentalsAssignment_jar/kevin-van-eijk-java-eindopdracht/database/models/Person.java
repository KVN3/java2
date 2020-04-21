package database.models;

import java.time.LocalDate;
import java.time.Period;

public class Person implements Comparable<Person> {
    protected int id;
    protected Account account;

    public Account getAccount() {
        return account;
    }

    protected String forename;
    protected String surname;
    protected LocalDate dateOfBirth;

    public int getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person(int id, Account account, String forename, String surname, LocalDate dateOfBirth) {
        this.id = id;
        this.account = account;
        this.forename = forename;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(Account account, String forename, String surname, LocalDate dateOfBirth) {
        this.id = id;
        this.account = account;
        this.forename = forename;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public int compareTo(Person person) {
        if (this.id > person.id)
            return 1;
        else if (this.id < person.id)
            return -1;
        return 0;
    }
}
