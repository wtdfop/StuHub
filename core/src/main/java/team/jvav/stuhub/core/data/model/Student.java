package team.jvav.stuhub.core.data.model;

public class Student {
    private int id;
    private String name;
    private int belongingGroupId;

    public Student(int id, String name, int belongingGroupId) {
        this.id = id;
        this.name = name;
        this.belongingGroupId = belongingGroupId;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Student other = (Student) obj;
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

    public int getBelongingGroupId() {
        return belongingGroupId;
    }

    public void setBelongingGroupId(int belongingGroupId) {
        this.belongingGroupId = belongingGroupId;
    }
}
