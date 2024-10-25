package team.jvav.stuhub.core.util

import team.jvav.stuhub.core.data.DAO
import team.jvav.stuhub.core.data.model.Class
import team.jvav.stuhub.core.data.model.Group
import team.jvav.stuhub.core.data.model.Student

/**
 * 随机抽取工具类。可以随机抽取班级、小组、学生。
 */
object RandomUtil {
    /**
     * 从数据源中抽取一个随机班级。
     * @return 随机班级
     * @throws NoSuchElementException 如果数据源中没有班级，则抛出该异常。
     */
    fun getRandomClass(): Class = DAO.getClasses().random()

    /**
     * 从数据源中抽取指定班级中的一个随机小组。
     * @param classId 班级 ID
     * @return 随机小组。如果指定班级不存在，则返回 null。
     * @throws NoSuchElementException 如果指定班级中没有小组，则抛出该异常。
     */
    fun getRandomGroupFromClass(classId: Int): Group? = DAO.getClassByID(classId)?.groups?.random()

    /**
     * 从数据源中抽取指定班级中的一个随机学生。
     * @param classId 班级 ID
     * @return 随机学生。如果指定班级，则返回 null。
     * @throws NoSuchElementException 如果指定班级中没有学生，则抛出该异常。
     */
    fun getRandomStudentFromClass(classId: Int): Student? = DAO.getClassByID(classId)?.students?.random()

    /**
     * 从数据源中抽取指定班级的指定小组中的一个随机学生。
     * @param classId 班级 ID
     * @param groupId 小组 ID
     * @return 随机学生。如果指定班级或小组不存在，则返回 null。
     * @throws NoSuchElementException 如果指定小组中没有学生，则抛出该异常。
     */
    fun getRandomStudentFromGroup(classId: Int, groupId: Int): Student? =
        DAO.getClassByID(classId)?.groups?.find { it.id == groupId }?.students?.random()
}