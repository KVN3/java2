package database;

import database.models.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Database {
    private int personIndex;
    private Set<Person> persons;
    private int groupIndex;
    private Set<Group> groups;
    private int reportIndex;
    private Set<Report> reports;

    public Database() {
        init();
    }

    public void init() {
        personIndex = 0;
        persons = new TreeSet<Person>();
        groups = new TreeSet<Group>();
        reports = new TreeSet<Report>();

        Student s1 = new Student(personIndex++, new Account("emma", "emma12"), "Emma", "Fluyt", LocalDate.of(1999, 10, 7), NewGroup("IT-A1"));
        persons.add(s1);
        reports.add(new Report(reportIndex++, s1, 60, 60, 45, 22));

        s1 = new Student(personIndex++, new Account("1", "1"), "Jimmy", "Peckers", LocalDate.of(2001, 8, 28), NewGroup("IT-A1"));
        persons.add(s1);
        reports.add(new Report(reportIndex++, s1, 10, 55, 10, 22));

        persons.add(new Teacher(personIndex++, new Account("mrteach", "1", AccessLevel.EDITOR), "Tim", "Teacher", LocalDate.of(1992, 5, 22), 5000.8));
        persons.add(new Teacher(personIndex++, new Account("mrm", "1", AccessLevel.ADMIN), "Bernie", "Cappa", LocalDate.of(1989, 2, 11), 5400));
    }

    public Account getAccount(String username, String password) {
        for (Person person : persons) {
            if (person.getAccount().getUsername().trim().equals(username.trim()) && person.getAccount().getPassword().trim().equals(password.trim()))
                return person.getAccount();
        }

        return null;
    }

    public Set<Student> getStudents() {
        Set<Student> students = new TreeSet<Student>();

        for (Person person : persons) {
            if (person instanceof Student)
                students.add((Student) person);
        }
        return students;
    }

    public Set<Teacher> getTeachers() {
        Set<Teacher> teachers = new TreeSet<Teacher>();

        for (Person person : persons) {
            if (person instanceof Teacher)
                teachers.add((Teacher) person);
        }
        return teachers;
    }

    public boolean AddPerson(Person person) {
        person.setId(personIndex++);

        // Students have reports
        if(person instanceof Student)
            reports.add(new Report(reportIndex++, person, 0, 0, 0, 0));

        return persons.add(person);
    }

    // Can't really figure out how to stop any other class from creating a Group object that's not exclusive to the list, but this is the right way
    public Group NewGroup(String groupName) {
        for (Group group :
                groups) {
            if (group.groupName == groupName)
                return group;
        }

        Group newGroup = new Group(groupIndex++, groupName);
        groups.add(newGroup);
        return newGroup;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public Person getPerson(int personId) {
        for (Person person : persons)
            if (person.getId() == personId)
                return person;

        return null;
    }

    public Report getReport(int studentId) {
//        Person person = getPerson(studentId);
//
//        // Only student persons have reports
//        if (!(person instanceof Student))
//            return null;
//
//        Student student = (Student) person;

        for (Report report : reports)
            if (report.getPerson().getId() == studentId)
                return report;

        return null;
    }

    public void updateReport(Report report, int javaGrade, int cSharpGrade, int pythonGrade, int phpGrade){
        reports.remove(report);
        report.setJavaGrade(javaGrade);
        report.setcSharpGrade(cSharpGrade);
        report.setPythonGrade(pythonGrade);
        report.setPhpGrade(phpGrade);
        reports.add(report);
    }
}
