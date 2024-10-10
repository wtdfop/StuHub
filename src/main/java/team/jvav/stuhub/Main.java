package team.jvav.stuhub;

import team.jvav.stuhub.data.model.Class;
import team.jvav.stuhub.data.model.Group;
import team.jvav.stuhub.data.model.Student;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Class c = new Class(1);
        for (int i = 0; i < 100; i++) {
            ArrayList<Student> students = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                students.add(new Student(i * 10 + j, "Stu" + (i * 10 + j)));
            }
            Group group = new Group(i + 1, "Group" + (i + 1), students);
            c.addGroup(group);
        }
        ArrayList<Student> randomStudents = c.getRandomStudents(10);
        for (Student student : randomStudents) {
            System.out.println("Stu" + student.getId());
        }
    }
}
