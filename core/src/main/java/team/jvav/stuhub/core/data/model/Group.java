package team.jvav.stuhub.core.data.model;

import java.util.ArrayList;

public class Group {
    private int id;
    private String name;
    private ArrayList<Student> students;
    private int score;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
        this.score = 0;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Group other = (Group) obj;
            return this.id == other.id;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public boolean addStudent(Student student) {
        return this.students.add(student);
    }

    public boolean removeStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public boolean hasStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return true;
            }
        }
        return false;
    }

    public Student getStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void clearScore() {
        this.score = 0;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void subScore(int score) {
        this.score -= score;
    }
}
