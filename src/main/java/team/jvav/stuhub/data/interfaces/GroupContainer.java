package team.jvav.stuhub.data.interfaces;

import team.jvav.stuhub.data.model.Group;

import java.util.ArrayList;

public interface GroupContainer {
    ArrayList<Group> getGroups();

    void addGroup(Group group);

    void removeGroup(Group group);

    Group getGroup(int id);

    boolean containsGroup(int id);

    ArrayList<Group> getRandomGroups(int count);

    Group getRandomGroup();
}
