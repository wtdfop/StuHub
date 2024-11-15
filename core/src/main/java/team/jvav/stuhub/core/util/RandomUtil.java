package team.jvav.stuhub.core.util;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.core.data.model.Student;

import java.util.ArrayList;

public class RandomUtil {
    /**
     * 从指定班级中随机选取一个学生
     *
     * @return 随机学生。班级不存在或学生列表为空时返回null
     */
    public static Student getRandomStudentFromClass(int classId) {
        Class c = DAO.getClassByID(classId);
        if (c == null || c.getStudents().isEmpty()) return null;
        ArrayList<Student> students = c.getStudents();
        for (Group g : c.getGroups()) {
            students.addAll(g.getStudents());
        }
        return students.get((int) (Math.random() * students.size()));
    }

    /**
     * 从指定班级中随机选取一个小组
     *
     * @return 随机小组。班级不存在或小组列表为空时返回null
     */
    public static Group getRandomGroupFromClass(int classId) {
        Class c = DAO.getClassByID(classId);
        if (c == null || c.getGroups().isEmpty()) return null;
        ArrayList<Group> groups = c.getGroups();
        return groups.get((int) (Math.random() * groups.size()));
    }

    /**
     * 从指定班级的指定小组中随机选取一个学生
     *
     * @return 随机学生。班级或小组不存在或学生列表为空时返回null
     */
    public static Student getRandomStudentFromGroup(int classId, int groupId) {
        Class c = DAO.getClassByID(classId);
        if (c == null) return null;
        Group g = c.getGroup(groupId);
        if (g == null || g.getStudents().isEmpty()) return null;
        ArrayList<Student> students = g.getStudents();
        return students.get((int) (Math.random() * students.size()));
    }
}