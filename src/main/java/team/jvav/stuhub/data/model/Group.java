package team.jvav.stuhub.data.model;

import java.util.ArrayList;

/**
 * 描述小组的类
 */
public class Group {
    /**
     * 此小组的唯一标识符
     */
    private int id;

    /**
     * 此小组的名称
     */
    private String name;

    /**
     * 此小组包含的学生
     */
    private ArrayList<Student> students;

    /**
     * 获取此小组的唯一标识符
     *
     * @return 此小组的唯一标识符
     */
    public int getId() {
        return id;
    }

    /**
     * 获取此小组的名称
     *
     * @return 此小组的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取此小组的所有学生
     *
     * @return 此小组的所有学生的数组
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * 构造一个完整的小组对象
     *
     * @param id       此小组的唯一标识符
     * @param name     此小组的名称
     * @param students 此小组包含的学生
     */
    public Group(int id, String name, ArrayList<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    /**
     * 为此小组添加一个学生
     *
     * @param student 要添加的学生
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * 为此小组删除一个学生
     *
     * @param student 要删除的学生
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * 从此小组中随机抽取若干个学生
     *
     * @param count 要抽取的学生个数
     * @return 返回此小组中随机的count个学生
     * @throws IllegalArgumentException 当count不为正整数，或count大于此小组中的学生个数时，抛出此异常
     */
    public ArrayList<Student> getRandomStudents(int count) {
        if (count < 1) throw new IllegalArgumentException("参数count应为正整数");
        if (students.size() < count)
            throw new IllegalArgumentException("参数count: " + count + " 大于此小组中的学生个数" + students.size());
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
