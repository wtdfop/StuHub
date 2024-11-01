package team.jvav.stuhub.core.data;

import team.jvav.stuhub.core.data.model.Class;

import java.util.ArrayList;

/**
 * DAO类，负责管理数据源并提供数据操作接口。
 */
public class DAO {
    /**
     * 存储所有班级信息的列表。
     * <p>
     * 此列表是DAO对数据源的缓存，以避免频繁访问数据源。
     */
    private ArrayList<Class> classes = DataSource.loadData();

    /**
     * 创建一个班级。若已存在则不重复创建。
     *
     * @param classID 班级ID
     * @return 是否成功创建并保存。当班级已存在时不再重复创建并返回false。
     */
    public boolean createClass(int classID) {
        for (Class c : classes) {
            if (c.getId() == classID) return false;
        }
        classes.add(new Class(classID));
        return DataSource.saveData(classes);
    }

    /**
     * 删除一个班级。若不存在则不进行任何操作。
     *
     * @param classID 班级ID
     * @return 是否成功删除并保存。当班级不存在时不进行任何操作并返回false。
     */
    public boolean deleteClass(int classID) {
        for (Class c : classes) {
            if (c.getId() == classID) {
                classes.remove(c);
                return DataSource.saveData(classes);
            }
        }
        return false;
    }

    /**
     * 根据班级ID获取班级。
     *
     * @param classID 班级ID
     * @return 班级对象。若不存在则返回null。
     */
    public Class getClassByID(int classID) {
        for (Class c : classes) {
            if (c.getId() == classID) return c;
        }
        return null;
    }

    /**
     * 更新一个班级。若目前不存在则不进行任何操作。
     *
     * @param clazz 新的班级对象
     * @return 是否成功更新并保存。当班级不存在时不进行任何操作并返回false。
     */
    public boolean updateClass(Class clazz) {
        for (int i = 0; i < classes.size(); i++) {
            if (clazz.getId() == classes.get(i).getId()) {
                classes.set(i, clazz);
                return DataSource.saveData(classes);
            }
        }
        return false;
    }

    //TODO: 学生、小组的增删查改方法
}