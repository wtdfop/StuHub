package team.jvav.stuhub.swing.ui.constants;

import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.core.data.model.Student;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * UI窗口常量
 */
public class FrameConstants {
    public static final String welcome = "Welcome to Team Jvav StuHub";
    public static final String title = "Team Jvav StuHub";
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // 小组、学生等文件路径
    public static final String FILE_PATH = "i:/starschool/classes/";
    // 班级路径
    public static String CLASS_PATH = "";
    // 存放当前班级的小组和学生
    public static LinkedHashMap<Group, java.util.List<Student>> groups = new LinkedHashMap<>();
    // 存放当前班级的所有学生
    public static List<Student> students = new ArrayList<>();
    // 缺勤扣5分
    public static final int ABSENTEEISM_SCORE = 5;
    // 请假扣2分
    public static final int LEAVE_SCORE = 2;
    // 回答问题正确加3分
    public static final int ANSWER_QUESTION = 3;

    // 当前班级ID。-1表示未选择班级
    public static int currentClassId = -1;
}
