package team.jvav.stuhub.data.model;

import team.jvav.stuhub.data.interfaces.ClassElement;
import team.jvav.stuhub.data.interfaces.StudentContainer;

import java.util.ArrayList;

/**
 * 描述小组的类
 */
public class Group implements ClassElement, StudentContainer {
    /**
     * 此小组的唯一标识符
     */
    private int id;

    /**
     * 此小组的名称
     */
    private String name;

    /**
     * 此小组的成绩
     *
     * @author wtdfop
     */
    private int score;

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
     * 获取此小组的成绩
     *
     * @return 此小组的成绩
     * @author wtdfop
     */
    public int getScore() {
        return score;
    }

    /**
     * 设置此小组的成绩
     *
     * @param score 要设置的成绩
     * @author wtdfop
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 增加此小组的成绩
     *
     * @param score 要增加的成绩
     * @author wtdfop
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * 减少此小组的成绩
     *
     * @param score 要减少的成绩
     * @author wtdfop
     */
    public void subScore(int score) {
        this.score -= score;
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
     * 构造一个最小化的小组对象，不包含任何学生，小组名称默认跟随id
     *
     * @param id 此小组的唯一标识符
     */
    public Group(int id) {
        this.id = id;
        this.name = "Group " + id;
        this.students = new ArrayList<>();
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
     * 从此小组中获取指定id的学生
     *
     * @param id 要获取的学生的id
     * @return 目标学生对象。如果此小组中找不到对应id的学生则返回null
     */
    public Student getStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    /**
     * 判断此小组中是否包含指定id的学生
     *
     * @param id 要查找的学生的id
     * @return true：包含；false：不包含
     */
    public boolean containsStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) return true;
        }
        return false;
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

    /**
     * 从此小组中随机抽取一个学生
     *
     * @return 返回此小组中随机的一个学生
     */
    public Student getRandomStudent() {
//        return students.get((int) (Math.random() * (students.size())));
        return getRandomStudents(1).get(0);
    }

    @Override
    public boolean inClass(Class c) {
        return c.containsGroup(this.id);
    }
}
