package team.jvav.stuhub.data.model;

import team.jvav.stuhub.data.interfaces.ClassElement;
import team.jvav.stuhub.data.interfaces.GroupElement;

/**
 * 描述学生的类
 */
public class Student implements ClassElement, GroupElement {
    /**
     * 此学生的唯一标识符
     */
    private int id;

    /**
     * 此学生的姓名
     */
    private String name;

    /**
     * 获取此学生的唯一标识符
     *
     * @return 此学生的唯一标识符
     */
    public int getId() {
        return id;
    }

    /**
     * 获取此学生的姓名
     *
     * @return 此学生的姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 构造一个完整的学生对象
     *
     * @param id   此学生的唯一标识符
     * @param name 此学生的姓名
     */
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean inClass(Class c) {
        return c.containsStudent(this.id);
    }

    @Override
    public boolean inGroup(Group group) {
        return group.containsStudent(this.id);
    }
}
