package team.jvav.stuhub.swing;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.DataSource;
import team.jvav.stuhub.swing.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        try {
            if (DataSource.init()) {
                DAO.createClass(1);
                for (int i = 1; i <= 110; i++) {
                    DAO.createStudentInClass(1, i, "Stu" + i);
                }
                for (int i = 1; i <= 10; i++) {
                    DAO.createGroupInClass(1, i, "Group" + i);
                }
                for (int i = 1; i <= 100; i++) {
                    DAO.addStudentToGroup(1, i, (i - 1) / 10 + 1);
                }
            }
        } catch (Exception e) {
            System.out.println("数据库初始化失败");
        }
        new MainFrame(); // 直接启动主窗口
    }
}