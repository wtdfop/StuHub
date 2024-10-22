package team.jvav.stuhub.core.data.interfaces;

import team.jvav.stuhub.core.data.model.Class;

/**
 * 班级元素接口
 */
public interface ClassElement {
    /**
     * 判断此元素是否在指定班级中
     *
     * @param c 指定的班级
     * @return 是否在指定班级中
     */
    boolean inClass(Class c);
}
