package main.handlers;

import database.Database;
import database.models.Report;
import database.models.Student;

import java.io.*;
import java.text.MessageFormat;
import java.util.Set;

public class AdminHandler extends BaseHandler {
    public AdminHandler(Database database) {
        super(database);
    }

    public void saveReportsToFileSystem(Set<Report> reports) {
        for (Report report : reports) {
            Student student = (Student) report.getPerson();
            File file = new File(MessageFormat.format("{2} {0} {1}.doc", student.getForename(), student.getSurname(), student.getId()));

            try (FileOutputStream fos = new FileOutputStream(file)) {
                String dataToWrite = report.getPrintInfo();
                fos.write(dataToWrite.getBytes());
                fos.flush();
                System.out.println(MessageFormat.format("Exported to: {2} {0} {1}.doc", student.getForename(), student.getSurname(), student.getId()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("fnf");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ioe");
            }
        }
    }
}
