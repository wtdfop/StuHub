package team.jvav.stuhub.swing;

import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.swing.ui.MainFrame;
import team.jvav.stuhub.core.data.model.Student;

import java.util.ArrayList;

public class Main {
    public static Class c = new Class(1);

    public static void main(String[] args) {
        // 初始化组和学生
        for (int i = 0; i < 100; i++) {
            ArrayList<Student> students = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                students.add(new Student(i * 10 + j, "Stu" + (i * 10 + j)));
            }
            Group group = new Group(i + 1, "Group" + (i + 1), students);
            c.addGroup(group);
        }
        new MainFrame(); // 直接启动主窗口
    }
}