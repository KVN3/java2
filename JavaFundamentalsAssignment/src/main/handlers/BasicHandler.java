package main.handlers;

import database.Database;
import database.models.Student;
import database.models.Teacher;
import main.handlers.models.ActionResult;

public class BasicHandler extends BaseHandler {
    public BasicHandler(Database database) {
        super(database);
    }

    protected ActionResult displayStudents() {
        String formattingString = "%-6s%-13s%-12s%-13s%-7s%-10s";

        System.out.println("LIST OF STUDENTS");
        System.out.format(formattingString, "Id", "FirstName", "LastName", "BirthDate", "Age", "Group");
        System.out.println();
        System.out.format(formattingString, "**", "*********", "********", "*********", "***", "*****");
        System.out.println();

        for (Student student : database.getStudents()) { student.printAsRow(); }

        return ActionResult.CONTINUE;
    }

    protected ActionResult displayTeachers() {
        String formattingString = "%-6s%-13s%-12s%-13s%-7s%-11s";

        System.out.println("LIST OF TEACHERS");
        System.out.format(formattingString, "Id", "FirstName", "LastName", "BirthDate", "Age", "Salary");
        System.out.println();
        System.out.format(formattingString, "**", "*********", "********", "*********", "***", "******");
        System.out.println();

        for (Teacher teacher : database.getTeachers()) { teacher.printAsRow(); }

        return ActionResult.CONTINUE;
    }

    protected ActionResult exitProgram() {
        System.out.print("Exiting program...");

        return ActionResult.EXIT;
    }
}
