package team.jvav.stuhub.core.data.interfaces;

import team.jvav.stuhub.core.data.model.Group;

/**
 * 小组元素接口
 */
public interface GroupElement {
    /**
     * 判断此元素是否在指定小组中
     *
     * @param group 指定的小组
     * @return 是否在指定小组中
     */
    boolean inGroup(Group group);
}
