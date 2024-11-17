package team.jvav.stuhub.core.data.model;

import java.util.ArrayList;

public class Class {
    private int id;
    private String name;
    private ArrayList<Group> groups;
    private ArrayList<Student> students;

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
        this.groups = new ArrayList<>();
        this.students = new ArrayList<>();
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
                ", students=" + students +
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

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public boolean addGroup(Group group) {
        return groups.add(group);
    }

    public boolean addStudent(Student student) {
        return students.add(student);
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
        for (Student student : students) {
            if (student.getId() == studentId) {
                students.remove(student);
                return true;
            }
        }
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
        for (Student student : students) {
            if (student.getId() == studentId) return true;
        }
        for (Group group : groups) {
            if (group.hasStudent(studentId)) return true;
        }
        return false;
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
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        for (Group group : groups) {
            if (group.hasStudent(studentId)) {
                return group.getStudent(studentId);
            }
        }
        return null;
    }
}
