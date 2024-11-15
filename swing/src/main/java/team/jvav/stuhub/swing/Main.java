//package team.jvav.stuhub.swing;
//
//import team.jvav.stuhub.core.data.DAO;
//import team.jvav.stuhub.core.data.DataSource;
//import team.jvav.stuhub.swing.ui.MainFrame;
//
//public class Main {
//    public static void main(String[] args) {
//        DataSource.INSTANCE.init();
//        DAO.INSTANCE.createClass(1);
//        // 初始化组和学生
//        for (int i = 0; i < 100; i++) {
//            DAO.INSTANCE.createGroupInClass(1, i + 1, "Group" + (i + 1));
//            for (int j = 0; j < 10; j++) {
//                DAO.INSTANCE.createStudentInClass(1, i * 10 + j, "Stu" + (i * 10 + j));
//                DAO.INSTANCE.addStudentToGroup(1, i + 1, i * 10 + j);
//            }
//        }
//        new MainFrame(); // 直接启动主窗口
//    }
//}