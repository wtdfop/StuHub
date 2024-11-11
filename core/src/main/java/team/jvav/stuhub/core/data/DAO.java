package team.jvav.stuhub.core.data;

import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.core.data.model.Student;

import java.util.ArrayList;

/**
 * DAO类，负责管理数据源并提供数据操作接口。
 */
public class DAO {
    /**
     * 存储所有班级信息的列表。
     * <p>
     * 此列表是DAO对数据源的缓存，以避免频繁访问数据源。
     */
    private static final ArrayList<Class> classes = DataSource.loadData();

    /**
     * 保存所有班级信息到数据源。
     *
     * @return 是否成功保存。
     */
    private static boolean saveData() {
        return DataSource.saveData(classes);
    }

    /**
     * 创建一个班级。
     *
     * @param classID 班级ID
     * @return 是否成功创建并保存。当班级已存在时不再重复创建并返回false。
     */
    public static boolean createClass(int classID) {
        for (Class c : classes) {
            if (c.getId() == classID) return false;
        }
        return classes.add(new Class(classID)) && saveData();
    }

    /**
     * 删除一个班级。
     *
     * @param classID 班级ID
     * @return 是否成功删除并保存。当班级不存在时不进行任何操作并返回false。
     */
    public static boolean deleteClass(int classID) {
        for (Class c : classes) {
            if (c.getId() == classID) {
                return classes.remove(c) && saveData();
            }
        }
        return false;
    }

    /**
     * 根据班级ID获取班级。
     *
     * @param classID 班级ID
     * @return 班级对象。若不存在则返回null。
     */
    public static Class getClassByID(int classID) {
        for (Class c : classes) {
            if (c.getId() == classID) return c;
        }
        return null;
    }

    /**
     * 更新一个班级。
     *
     * @param clazz 新的班级对象
     * @return 是否成功更新并保存。当班级不存在时不进行任何操作并返回false。
     */
    public static boolean updateClass(Class clazz) {
        for (int i = 0; i < classes.size(); i++) {
            if (clazz.getId() == classes.get(i).getId()) {
                classes.set(i, clazz);
                return saveData();
            }
        }
        return false;
    }

    /**
     * 在指定班级中创建学生。
     *
     * @return 是否成功创建并保存。当班级不存在时不进行任何操作并返回false。
     */
    public static boolean createStudentInClass(int classID, int studentID, String studentName) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        return c.addStudent(new Student(studentID, studentName)) && updateClass(c);
    }

    /**
     * 删除指定班级中的学生。
     *
     * @return 是否成功删除并保存。当班级不存在时不进行任何操作并返回false。
     */
    public static boolean deleteStudentInClass(int classID, int studentID) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        return c.removeStudent(studentID) && updateClass(c);
    }

    /**
     * 在指定班级的指定小组中创建学生。
     *
     * @return 是否成功创建并保存。当班级或小组不存在时不进行任何操作并返回false。
     */
    public static boolean createStudentInGroup(int classID, int groupID, int studentID, String studentName) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        Group g = getGroupByIDInClass(classID, groupID);
        if (g == null) return false;
        return g.addStudent(new Student(studentID, studentName)) && updateGroupInClass(classID, g);
    }

    /**
     * 删除指定班级的指定小组中的学生。
     *
     * @return 是否成功删除并保存。当班级或小组不存在时不进行任何操作并返回false。
     */
    public static boolean deleteStudentInGroup(int classID, int groupID, int studentID) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        Group g = getGroupByIDInClass(classID, groupID);
        if (g == null) return false;
        return g.removeStudent(studentID) && updateGroupInClass(classID, g);
    }

    /**
     * 获取指定班级的指定学生。
     *
     * @return 学生对象。若不存在则返回null。
     */
    public static Student getStudentByIDInClass(int classID, int studentID) {
        Class c = getClassByID(classID);
        if (c == null) return null;
        return c.getStudent(studentID);
    }

    /**
     * 更新指定班级的指定学生。
     *
     * @return 是否成功更新并保存。当班级或学生不存在时不进行任何操作并返回false。
     */
    public static boolean updateStudentInClass(int classID, Student student) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        return c.removeStudent(student.getId()) && c.addStudent(student) && updateClass(c);
    }

    /**
     * 在指定班级中创建小组。
     *
     * @return 是否成功创建并保存。当班级不存在时不进行任何操作并返回false。
     */
    public static boolean createGroupInClass(int classID, int groupID, String groupName) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        return c.addGroup(new Group(groupID, groupName)) && updateClass(c);
    }

    /**
     * 删除指定班级中的小组。
     *
     * @return 是否成功删除并保存。当班级不存在时不进行任何操作并返回false。
     */
    public static boolean deleteGroupInClass(int classID, int groupID) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        return c.removeGroup(groupID) && updateClass(c);
    }

    /**
     * 获取指定班级的指定小组。
     *
     * @return 小组对象。若不存在则返回null。
     */
    public static Group getGroupByIDInClass(int classID, int groupID) {
        Class c = getClassByID(classID);
        if (c == null) return null;
        return c.getGroup(groupID);
    }

    /**
     * 更新指定班级的指定小组。
     *
     * @return 是否成功更新并保存。当班级或小组不存在时不进行任何操作并返回false。
     */
    public static boolean updateGroupInClass(int classID, Group group) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        return c.removeGroup(group.getId()) && c.addGroup(group) && updateClass(c);
    }

    /**
     * 将指定班级的指定学生添加到指定小组。
     *
     * @return 是否成功添加并保存。当班级不存在、学生不在此班级、小组不在此班级、学生已在小组中时不进行任何操作并返回false。
     */
    public static boolean addStudentToGroup(int classID, int studentID, int groupID) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        Group g = getGroupByIDInClass(classID, groupID);
        if (g == null) return false;
        Student s = getStudentByIDInClass(classID, studentID);
        if (s == null) return false;
        if (!c.hasGroup(groupID) || !c.hasStudent(studentID) || g.hasStudent(studentID)) {
            return false;
        }
        return c.removeStudent(studentID) && g.addStudent(s) && updateGroupInClass(classID, g);
    }

    /**
     * 将指定班级的指定学生从指定小组中移除。
     *
     * @return 是否成功移除并保存。当班级不存在、学生不在此班级、小组不在此班级、学生不在此小组中时不进行任何操作并返回false。
     */
    public static boolean removeStudentFromGroup(int classID, int studentID, int groupID) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        Group g = getGroupByIDInClass(classID, groupID);
        if (g == null) return false;
        Student s = g.getStudent(studentID);
        return s != null && g.removeStudent(studentID) && c.addStudent(s) && updateGroupInClass(classID, g);
    }
}