package team.jvav.stuhub.swing;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.DataSource;
import team.jvav.stuhub.swing.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            DAO.createClass(i, "class" + i);
            for (int j = 0; j < 10; j++) {
                DAO.createGroupInClass(i, j, "group" + j);
                for (int k = 0; k < 5; k++) {
                    DAO.createStudentInGroup(i, j, k, "student" + k);
                }
            }
        }
        new MainFrame(); // 直接启动主窗口
    }
}