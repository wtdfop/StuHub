package team.jvav.stuhub.core.data;

import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.core.data.model.Student;

import java.util.ArrayList;
import java.util.List;

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
    public static boolean createClass(int classID, String className) {
        for (Class c : classes) {
            if (c.getId() == classID) return false;
        }
        return classes.add(new Class(classID, className)) && saveData();
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
     * 获取所有班级。没有班级时返回空列表。
     */
    public static List<Class> getAllClasses() {
        return classes;
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
     * 在指定班级的指定小组中创建学生。
     *
     * @return 是否成功创建并保存。当班级或小组不存在时不进行任何操作并返回false。
     */
    public static boolean createStudentInGroup(int classID, int groupID, int studentID, String studentName) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        Group g = getGroupByIDInClass(classID, groupID);
        if (g == null) return false;
        return g.addStudent(new Student(studentID, studentName, groupID)) && updateGroupInClass(classID, g);
    }

    /**
     * 删除指定班级的指定小组中的指定学生。
     *
     * @return 是否成功删除并保存。当班级或小组不存在或学生不存在时不进行任何操作并返回false。
     */
    public static boolean deleteStudentInGroup(int classID, int groupID, int studentID) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        Group g = getGroupByIDInClass(classID, groupID);
        if (g == null) return false;
        return g.removeStudent(studentID) && updateGroupInClass(classID, g);
    }

    /**
     * 获取指定班级的指定小组中的指定学生。
     *
     * @return 学生对象或null
     */
    public static Student getStudentInGroup(int classID, int groupID, int studentID) {
        Class c = getClassByID(classID);
        if (c == null) return null;
        Group g = getGroupByIDInClass(classID, groupID);
        if (g == null) return null;
        return g.getStudent(studentID);
    }

    /**
     * 获取指定班级中的指定学生。
     *
     * @return 学生对象或null
     */
    public static Student getStudentInClass(int classID, int studentID) {
        Class c = getClassByID(classID);
        if (c == null) return null;
        for (Group g : c.getAllGroups()) {
            if (g.hasStudent(studentID)) return g.getStudent(studentID);
        }
        return null;
    }

    /**
     * 更新指定班级的指定小组中的指定学生。
     *
     * @return 是否成功更新并保存。当班级或小组不存在或学生不存在时不进行任何操作并返回false。
     */
    public static boolean updateStudentInGroup(int classID, int groupID, int studentId, String studentName) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        Group g = getGroupByIDInClass(classID, groupID);
        if (g == null) return false;
        if (g.hasStudent(studentId)) {
            g.removeStudent(studentId);
            g.addStudent(new Student(studentId, studentName, groupID));
            return updateGroupInClass(classID, g);
        } else return false;
    }

    /**
     * 更新指定班级的的指定学生。
     *
     * @return 是否成功更新并保存。当班级不存在或学生不存在时不进行任何操作并返回false。
     */
    public static boolean updateStudentInClass(int classID, int studentID, String studentName) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        for (Group g : c.getAllGroups()) {
            if (g.hasStudent(studentID)) {
                g.removeStudent(studentID);
                g.addStudent(new Student(studentID, studentName, g.getId()));
                return updateGroupInClass(classID, g);
            }
        }
        System.out.println("Student not found in any group in class " + classID);
        return false;
    }

    /**
     * 将指定班级中指定小组的指定学生移动到另一个小组
     * @return 是否成功移动并保存。当班级或小组不存在或学生不存在或学生已在目标小组时不进行任何操作并返回false。
     */
    public static boolean moveStudentToGroup(int classID, int studentID, int oldGroupID, int newGroupID) {
        if (oldGroupID == newGroupID) {
            System.out.println("oldGroupID and newGroupID cannot be the same");
            return false;
        }
        Student s = getStudentInGroup(classID, oldGroupID, studentID);
        Group oldG = getGroupByIDInClass(classID, oldGroupID);
        Group newG = getGroupByIDInClass(classID, newGroupID);
        if (s == null || oldG == null || newG == null || !oldG.hasStudent(studentID)) {
            System.out.println("Student or group not found or student already in new group");
            return false;
        }
        oldG.removeStudent(studentID);
        newG.addStudent(new Student(studentID, s.getName(), newGroupID));
        return updateGroupInClass(classID, oldG) && updateGroupInClass(classID, newG);
    }

    /**
     * 在指定班级中创建小组。
     *
     * @return 是否成功创建并保存。当班级不存在或小组已存在时不进行任何操作并返回false。
     */
    public static boolean createGroupInClass(int classID, int groupID, String groupName) {
        Class c = getClassByID(classID);
        if (c == null) return false;
        if (c.hasGroup(groupID)) return false;
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

    public static ArrayList<Group> getGroupByClassId(int classId) {
        Class c = getClassByID(classId);
        if (c == null) return null;
        return c.getAllGroups();
    }
}