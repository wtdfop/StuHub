package team.jvav.stuhub.data.interfaces;

import team.jvav.stuhub.data.model.Student;

import java.util.ArrayList;

public interface StudentContainer {
    ArrayList<Student> getStudents();

    void addStudent(Student student);

    void removeStudent(Student student);

    Student getStudent(int id);

    boolean containsStudent(int id);

    ArrayList<Student> getRandomStudents(int count);

    Student getRandomStudent();
}
