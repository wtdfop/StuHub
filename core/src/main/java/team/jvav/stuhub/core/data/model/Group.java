package team.jvav.stuhub.core.data.model;

import java.util.ArrayList;

public class Group {
    private int id;
    private String name;
    private ArrayList<Student> students;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}
