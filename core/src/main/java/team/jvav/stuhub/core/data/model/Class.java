package team.jvav.stuhub.core.data.model;

import java.util.ArrayList;

public class Class {
    private int id;
    private String name;

    /**
     * 班级的小组列表。
     */
    private ArrayList<Group> groups;

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
        this.groups = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Class other = (Class) obj;
            return this.id == other.id;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name=" + name +
                ", groups=" + groups +
                "}";
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

    public ArrayList<Group> getAllGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> allStudents = new ArrayList<>();
        for (Group group : groups) {
            allStudents.addAll(group.getStudents());
        }
        return allStudents;
    }

    public boolean addGroup(Group group) {
        return groups.add(group);
    }

    public boolean removeGroup(int groupId) {
        for (Group group : groups) {
            if (group.getId() == groupId) {
                groups.remove(group);
                return true;
            }
        }
        return false;
    }

    public boolean removeStudent(int studentId) {
        for (Group group : groups) {
            if (group.hasStudent(studentId)) {
                group.removeStudent(studentId);
                return true;
            }
        }
        return false;
    }

    public boolean hasGroup(int groupId) {
        for (Group group : groups) {
            if (group.getId() == groupId) {
                return true;
            }
        }
        return false;
    }

    public boolean hasStudent(int studentId) {
        for (Student student : getAllStudents()) {
            if (student.getId() == studentId) return true;
        }
        return false;
    }

    /**
     * 判断指定学生是否在任何小组中。
     *
     * @return 小组 ID，如果指定学生不在任何小组中，则返回 -1。
     */
    public int hasStudentInAnyGroup(int studentId) {
        for (Group group : groups) {
            if (group.hasStudent(studentId)) {
                return group.getId();
            }
        }
        return -1;
    }

    public Group getGroup(int groupId) {
        for (Group group : groups) {
            if (group.getId() == groupId) {
                return group;
            }
        }
        return null;
    }

    public Student getStudent(int studentId) {
        for (Student student : getAllStudents()) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }
}
