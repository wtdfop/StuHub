package team.jvav.stuhub.core.data.model;

import java.util.ArrayList;

public class Class {
    private int id;
    private ArrayList<Group> groups;
    private ArrayList<Student> students;

    public Class(int id) {
        this.id = id;
        this.groups = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
