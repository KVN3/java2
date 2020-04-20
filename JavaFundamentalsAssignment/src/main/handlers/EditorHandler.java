package main.handlers;

import database.Database;
import database.models.AccessLevel;
import database.models.Account;
import database.models.Report;
import database.models.Student;
import main.handlers.models.ActionResult;

import java.text.MessageFormat;
import java.time.LocalDate;

public class EditorHandler extends BaseHandler {
    public EditorHandler(Database database) {
        super(database);
    }

    public ActionResult updateReport(int studentId) {
        Report report = database.getReport(studentId);

        int javaGrade = getGradeInput(MessageFormat.format("Current grade for Java is: {0} ... Enter (new) grade: ", report.getJavaGrade()));
        int cSharpGrade = getGradeInput(MessageFormat.format("Current grade for C# is: {0} ... Enter (new) grade: ", report.getcSharpGrade()));
        int pythonGrade = getGradeInput(MessageFormat.format("Current grade for Python is: {0} ... Enter (new) grade: ", report.getPythonGrade()));
        int phpGrade = getGradeInput(MessageFormat.format("Current grade for PHP is: {0} ... Enter (new) grade: ", report.getPhpGrade()));

        database.updateReport(report, javaGrade, cSharpGrade, pythonGrade, phpGrade);

        return ActionResult.CONTINUE_REPORTS;
    }

    private int getGradeInput(String requestMessage) {
        int newGrade = 0;

        try {
            System.out.print(requestMessage);
            String gradeInput = in.next();
            newGrade = Integer.parseInt(gradeInput);

            if (newGrade < 0 || newGrade > 100) {
                System.out.println("Not a valid grade number, must be between 0 and 100.");
                getGradeInput(requestMessage);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Not a valid grade number, entered a string.");
            getGradeInput(requestMessage);
        }

        return newGrade;
    }

    public ActionResult displayReports() {
        String formattingString = "%-13s%-13s%-12s%-13s%-7s%-10s%-8s%-10s%-10s%-7s";

        System.out.println("LIST OF RESULTS");
        System.out.println();
        System.out.format(formattingString, "Student Id", "FirstName", "LastName", "BirthDate", "Age", "Group", "Java", "CSharp", "Python", "PHP");
        System.out.println();
        System.out.format(formattingString, "**********", "*********", "********", "*********", "***", "*****", "****", "******", "******", "***");
        System.out.println();

        for (Report report : database.getReports()) {
            report.printAsRow();
        }

        return ActionResult.CONTINUE_REPORTS;
    }

    public void AddStudent() {
        System.out.println("ADD STUDENT");
        System.out.print("Choose a username: ");
        String usernameInput = in.next();
        System.out.print("Choose a password: ");
        String passwordInput = in.next();
        System.out.print("Enter first name: ");
        String firstNameInput = in.next();
        System.out.print("Enter last name: ");
        String lastNameInput = in.next();
        LocalDate dateOfBirth = GetDateOfBirthFromUser();
        System.out.print("Enter group: ");
        String groupNameInput = in.next();

        Student student = new Student(new Account(usernameInput, passwordInput, AccessLevel.BASIC), firstNameInput, lastNameInput, dateOfBirth, database.NewGroup(groupNameInput));
        if (database.AddPerson(student))
            System.out.print("The data was successfully added!");
        else
            System.out.print("Something went wrong.");
    }

    private LocalDate GetDateOfBirthFromUser() {
        while (true) {
            System.out.print("Please enter date of birth in MM/DD/YYYY: ");
            String dateOfBirthInput = in.next();

            try {
                String[] dateOfBirthParts = dateOfBirthInput.split("/");

                int year = Integer.parseInt(dateOfBirthParts[2]);
                int month = Integer.parseInt(dateOfBirthParts[0]);
                int dayOfMonth = Integer.parseInt(dateOfBirthParts[1]);

                if (year > 1900 && year < 2020 && month > 0 && month < 13 && dayOfMonth > 0 && dayOfMonth < 32)
                    return LocalDate.of(year, month, dayOfMonth);
            } catch (Exception ex) {

            }

            System.out.println("Invalid date format provided. Please try again.");
        }
    }
}
