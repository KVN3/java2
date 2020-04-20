package database.models;

import java.time.LocalDate;

public class Group implements Comparable<Group> {
    protected int id;

    public void setId(int id) {
        this.id = id;
    }

    public String groupName;

    public Group(int id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    @Override
    public int compareTo(Group group) {
        if (this.id > group.id)
            return 1;
        else if (this.id < group.id)
            return -1;
        return 0;
    }
}
