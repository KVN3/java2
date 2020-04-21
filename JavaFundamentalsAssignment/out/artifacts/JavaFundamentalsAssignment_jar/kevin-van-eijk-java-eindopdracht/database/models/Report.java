package database.models;

import java.io.Serializable;
import java.text.MessageFormat;

public class Report implements Comparable<Report> {
    private int id;
    private Person person;

    private int javaGrade;
    private int cSharpGrade;
    private int pythonGrade;
    private int phpGrade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getJavaGrade() {
        return javaGrade;
    }

    public void setJavaGrade(int javaGrade) {
        this.javaGrade = javaGrade;
    }

    public int getcSharpGrade() {
        return cSharpGrade;
    }

    public void setcSharpGrade(int cSharpGrade) {
        this.cSharpGrade = cSharpGrade;
    }

    public int getPythonGrade() {
        return pythonGrade;
    }

    public void setPythonGrade(int pythonGrade) {
        this.pythonGrade = pythonGrade;
    }

    public int getPhpGrade() {
        return phpGrade;
    }

    public void setPhpGrade(int phpGrade) {
        this.phpGrade = phpGrade;
    }

    public Report(int id, Person person, int javaGrade, int cSharpGrade, int pythonGrade, int phpGrade) {
        this.id = id;
        this.person = person;
        this.javaGrade = javaGrade;
        this.cSharpGrade = cSharpGrade;
        this.pythonGrade = pythonGrade;
        this.phpGrade = phpGrade;
    }

    public Report(Person person, int javaGrade, int cSharpGrade, int pythonGrade, int phpGrade) {
        this.person = person;
        this.javaGrade = javaGrade;
        this.cSharpGrade = cSharpGrade;
        this.pythonGrade = pythonGrade;
        this.phpGrade = phpGrade;
    }

    public void printAsRow() {
        if (person instanceof Student) {
            Student student = (Student) person;
            System.out.format("%-13d%-13s%-12s%-13s%-7d%-9s%-9d%-10d%-10d%-7d", person.id, student.forename, student.surname, student.dateOfBirth, student.getAge(), student.group.groupName,
                    javaGrade, cSharpGrade, pythonGrade, phpGrade);
        } else
            System.out.print("Teachers don't have reports.");
        System.out.println();
    }

    public String getPrintInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(MessageFormat.format("Report of student {0} {1}", person.forename, person.surname));
        sb.append("\n\n");
        sb.append(MessageFormat.format("Student Id   ....................   {0} \n", person.id));
        sb.append(MessageFormat.format("First Name   ....................   {0} \n", person.forename));
        sb.append(MessageFormat.format("Last Name   .....................   {0} \n", person.surname));
        sb.append(MessageFormat.format("Age   ...........................   {0} \n", person.getAge()));

        sb.append("\n             COURSES                 \n\n");

        sb.append(MessageFormat.format("Java   ..........................   {0} \n", javaGrade));
        sb.append(MessageFormat.format("CSharp   ........................   {0} \n", cSharpGrade));
        sb.append(MessageFormat.format("Python   ........................   {0} \n", pythonGrade));
        sb.append(MessageFormat.format("PHP   ...........................   {0} \n", phpGrade));

        sb.append("\n             RESULTS                 \n\n");

        int amountOfRetakes = getAmountOfRetakes();
        sb.append(MessageFormat.format("Result   ........................   {0} \n", getIsPassedString(amountOfRetakes)));
        sb.append(MessageFormat.format("Retakes   .......................   {0} \n", amountOfRetakes));
        sb.append("\n");

        return sb.toString();
    }

    private int getAmountOfRetakes() {
        int amountOfRetakes = 0;

        if (javaGrade < 55)
            amountOfRetakes++;
        if (cSharpGrade < 55)
            amountOfRetakes++;
        if (pythonGrade < 55)
            amountOfRetakes++;
        if (phpGrade < 55)
            amountOfRetakes++;

        return amountOfRetakes;
    }

    private boolean isPassed(int amountOfRetakes) {
        if (amountOfRetakes == 0)
            return true;
        else
            return false;
    }

    private String getIsPassedString(int amountOfRetakes) {
        if (isPassed(amountOfRetakes))
            return "Passed";
        else
            return "Not Passed";
    }

    @Override
    public int compareTo(Report report) {
        if (this.id > report.id)
            return 1;
        else if (this.id < report.id)
            return -1;
        return 0;
    }
}
