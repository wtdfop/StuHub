package team.jvav.stuhub.data.model;

import team.jvav.stuhub.data.interfaces.ClassContainer;

import java.util.ArrayList;

/**
 * @author wtdfop
 */
public class College implements ClassContainer {
    ArrayList<Class> classes;

    /**
     * 向班级容器中添加班级
     * @param clazz
     */
    @Override
    public void addClass(Class clazz) {
        classes.add(clazz);
    }
}
