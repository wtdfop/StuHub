package team.jvav.stuhub.data.model;

import java.util.ArrayList;

/**
 * 描述班级的类
 */
public class Class {
    /**
     * 此班级的唯一标识符
     */
    private int id;

    /**
     * 此班级包含的小组
     */
    private ArrayList<Group> groups;

    /**
     * 此班级包含的学生
     */
    private ArrayList<Student> students;

    /**
     * 获取此班级的唯一标识符
     *
     * @return 此班级的唯一标识符
     */
    public int getId() {
        return id;
    }

    /**
     * 获取此班级包含的小组
     *
     * @return 此班级包含的小组的数组
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * 获取此班级包含的学生
     *
     * @return 此班级包含的学生的数组
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * 构造完整的Class对象
     *
     * @param id       班级的唯一标识符
     * @param groups   班级包含的小组
     * @param students 班级包含的学生
     */
    public Class(int id, ArrayList<Group> groups, ArrayList<Student> students) {
        this.id = id;
        this.groups = groups;
        this.students = students;
    }

    /**
     * 构造空的Class对象。不包含任何小组和学生
     *
     * @param id 班级的唯一标识符
     */
    public Class(int id) {
        this.id = id;
        this.groups = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    /**
     * 为此班级添加一个小组，同时将小组中的学生也添加进此班级中
     *
     * @param group 要添加的小组
     */
    public void addGroup(Group group) {
        groups.add(group);
        students.addAll(group.getStudents());
    }

    /**
     * 从此班级中删除一个小组
     *
     * @param group 要删除的小组
     */
    public void removeGroup(Group group) {
        groups.remove(group);
    }

    /**
     * 为此班级添加一个学生
     *
     * @param student 要添加的学生
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * 为此班级删除一个学生
     *
     * @param student 要删除的学生
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * 从此班级中随机抽取若干个小组
     *
     * @param count 要抽取的小组个数
     * @return 返回此班级中随机的count个小组
     * @throws IllegalArgumentException 当count不为正整数，或count大于此班级中的小组个数时，抛出此异常
     */
    public ArrayList<Group> getRandomGroups(int count) {
        if (count < 1) throw new IllegalArgumentException("参数count应为正整数");
        if (groups.size() < count)
            throw new IllegalArgumentException("参数count: " + count + " 大于此班级中的小组个数" + groups.size());
        ArrayList<Group> result = new ArrayList<>();
        ArrayList<Group> groups = (ArrayList<Group>) this.groups.clone();
        for (int i = 0; i < count; i++) {
            Group g = groups.get((int) (Math.random() * (groups.size())));
            groups.remove(g);
            result.add(g);
        }
        return result;
    }

    /**
     * 从此班级中随机抽取若干个学生
     *
     * @param count 要抽取的学生个数
     * @return 返回此班级中随机的count个学生
     * @throws IllegalArgumentException 当count不为正整数，或count大于此班级中的学生个数时，抛出此异常
     */
    public ArrayList<Student> getRandomStudents(int count) {
        if (count < 1) throw new IllegalArgumentException("参数count应为正整数");
        if (students.size() < count)
            throw new IllegalArgumentException("参数count: " + count + " 大于此班级中的学生个数" + students.size());
        ArrayList<Student> result = new ArrayList<>();
        ArrayList<Student> students = (ArrayList<Student>) this.students.clone();
        for (int i = 0; i < count; i++) {
            Student s = students.get((int) (Math.random() * (students.size())));
            students.remove(s);
            result.add(s);
        }
        return result;
    }
}
