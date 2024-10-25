package team.jvav.stuhub.core.data

import team.jvav.stuhub.core.data.model.Class
import team.jvav.stuhub.core.data.model.Group
import team.jvav.stuhub.core.data.model.Student

/**
 * DAO类，负责管理数据源并提供数据操作接口。
 */
object DAO {
    /**
     * 存储所有班级信息的列表。
     *
     * 此列表是DAO对数据源的缓存，以避免频繁访问数据源。
     */
    private val classes: MutableList<Class> = DataSource.loadData()

    /**
     * 创建一个班级。若已存在则不重复创建。
     * @param classID 班级ID
     */
    fun createClass(classID: Int) {
        if (classes.any { it.id == classID }) return
        classes.add(Class(classID, mutableListOf(), mutableListOf()))
        DataSource.saveData(classes)
    }

    /**
     * 删除一个班级。若不存在则不进行任何操作。
     * @param classID 班级ID
     */
    fun deleteClass(classID: Int) {
        if (classes.removeIf { it.id == classID }) DataSource.saveData(classes)
    }

    /**
     * 根据班级ID获取班级。
     *
     * @param classID 班级ID
     * @return 班级对象。若不存在则返回null。
     */
    fun getClassByID(classID: Int) = classes.find { it.id == classID }

    /**
     * 获取所有班级列表。
     */
    fun getClasses() = classes.toList()

    /**
     * 更新一个班级。若目前不存在则不进行任何操作。
     * @param clazz 新的班级对象
     */
    fun updateClass(clazz: Class) {
        classes.removeIf { it.id == clazz.id } && classes.add(clazz)
        DataSource.saveData(classes)
    }

    /**
     * 在指定班级中创建一个小组。若班级不存在或小组已存在则不进行任何操作。
     * @param classID 班级ID
     * @param groupID 小组ID
     * @param groupName 小组名称
     */
    fun createGroupInClass(classID: Int, groupID: Int, groupName: String) {
        val clazz = classes.find { it.id == classID } ?: return
        if (clazz.groups.any { it.id == groupID }) return
        val group = Group(groupID, groupName, mutableListOf())
        clazz.groups.add(group)
        DataSource.saveData(classes)
    }

    /**
     * 删除指定班级中的一个小组。若班级或小组不存在则不进行任何操作。
     * @param classID 班级ID
     * @param groupID 小组ID
     */
    fun deleteGroupInClass(classID: Int, groupID: Int) {
        val clazz = classes.find { it.id == classID } ?: return
        clazz.groups.removeIf { it.id == groupID }
        DataSource.saveData(classes)
    }

    /**
     * 获取指定班级中的一个指定小组。
     * @param classID 班级ID
     * @param groupID 小组ID
     * @return 小组对象。若班级或小组不存在则返回null。
     */
    fun getGroupByIDInClass(classID: Int, groupID: Int) =
        classes.find { it.id == classID }?.groups?.find { it.id == groupID }

    /**
     * 获取指定班级中的所有小组列表。
     * @param classID 班级ID
     * @return 小组列表。若班级不存在则返回null。
     */
    fun getGroupsInClass(classID: Int) =
        classes.find { it.id == classID }?.groups?.toList()

    /**
     * 更新指定班级中的一个小组。若班级或小组不存在则不进行任何操作。
     * @param classID 班级ID
     * @param group 新的小组对象
     */
    fun updateGroupInClass(classID: Int, group: Group) {
        val clazz = classes.find { it.id == classID } ?: return
        clazz.groups.removeIf { it.id == group.id } && clazz.groups.add(group)
        DataSource.saveData(classes)
    }

    /**
     * 在指定班级中创建一个学生。若班级不存在或学生已存在则不进行任何操作。
     * @param classID 班级ID
     * @param studentID 学生ID
     * @param studentName 学生名称
     */
    fun createStudentInClass(classID: Int, studentID: Int, studentName: String) {
        val clazz = classes.find { it.id == classID } ?: return
        if (clazz.students.any { it.id == studentID }) return
        clazz.students.add(Student(studentID, studentName))
        DataSource.saveData(classes)
    }

    /**
     * 将指定班级中的指定学生加入到此班级的指定小组。若班级或小组或学生不存在，或学生已在小组中，则不进行任何操作。
     * @param classID 班级ID
     * @param groupID 小组ID
     * @param studentID 学生ID
     */
    fun addStudentToGroup(classID: Int, groupID: Int, studentID: Int) {
        val clazz = classes.find { it.id == classID } ?: return
        val group = clazz.groups.find { it.id == groupID } ?: return
        val student = clazz.students.find { it.id == studentID } ?: return
        if (group.students.any { it.id == studentID }) return
        group.students.add(student)
        updateGroupInClass(classID, group)
    }

    /**
     * 将指定班级中的指定学生从此班级的指定小组中移除。若班级或小组或学生不存在，或学生不在小组中，则不进行任何操作。
     * @param classID 班级ID
     * @param groupID 小组ID
     * @param studentID 学生ID
     */
    fun removeStudentFromGroup(classID: Int, groupID: Int, studentID: Int) {
        val clazz = classes.find { it.id == classID } ?: return
        val group = clazz.groups.find { it.id == groupID } ?: return
        val student = group.students.find { it.id == studentID } ?: return
        if (group.students.remove(student)) updateGroupInClass(classID, group)
    }

    /**
     * 删除指定班级中的一个学生，同时将其从此班级的所有小组中移除。若班级或学生不存在则不进行任何操作。
     * @param classID 班级ID
     * @param studentID 学生ID
     */
    fun deleteStudentInClass(classID: Int, studentID: Int) {
        val clazz = classes.find { it.id == classID } ?: return
        if (clazz.students.removeIf { it.id == studentID }) {
            for (group in clazz.groups) {
                group.students.removeIf { it.id == studentID }
            }
            DataSource.saveData(classes)
        }
    }

    /**
     * 获取指定班级中的所有学生列表。
     * @param classID 班级ID
     * @return 学生列表。若班级不存在则返回null。
     */
    fun getStudentsInClass(classID: Int) = classes.find { it.id == classID }?.students?.toList()

    /**
     * 获取指定班级中的一个指定学生。
     * @param classID 班级ID
     * @param studentID 学生ID
     * @return 学生对象。若班级或学生不存在则返回null。
     */
    fun getStudentByIDInClass(classID: Int, studentID: Int) = getStudentsInClass(classID)?.find { it.id == studentID }

    /**
     * 更新指定班级中的一个学生。若班级或学生不存在则不进行任何操作。
     * @param classID 班级ID
     * @param student 新的学生对象
     */
    fun updateStudentInClass(classID: Int, student: Student) {
        val clazz = classes.find { it.id == classID } ?: return
        clazz.students.removeIf { it.id == student.id } && clazz.students.add(student)
        DataSource.saveData(classes)
    }

    /**
     * 更新指定班级中的指定小组中的一个学生。若班级或小组或学生不存在则不进行任何操作。
     * @param classID 班级ID
     * @param groupID 小组ID
     * @param student 新的学生对象
     */
    fun updateStudentInGroup(classID: Int, groupID: Int, student: Student) {
        val clazz = classes.find { it.id == classID } ?: return
        val group = clazz.groups.find { it.id == groupID } ?: return
        group.students.removeIf { it.id == student.id } && group.students.add(student)
        updateGroupInClass(classID, group)
    }
}