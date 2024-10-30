package team.jvav.stuhub.core.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import team.jvav.stuhub.core.data.model.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据源类，负责管理数据源文件
 */
public class DataSource {
    /**
     * 数据源文件
     */
    private static final File dataFile = new File("StuHubData/data.json");

    /**
     * 检查数据源文件是否存在
     */
    public static boolean exists() {
        return dataFile.exists();
    }

    /**
     * 初始化数据源文件。如果文件不存在，则创建新文件并写入空列表
     *
     * @return 是否成功初始化数据源文件。如果初始化失败或文件已存在，则返回false
     */
    public static boolean init() {
        if (!dataFile.exists()) {
            if (!dataFile.getParentFile().mkdirs()) return false;
            try (FileWriter writer = new FileWriter(dataFile)) {
                if (!dataFile.createNewFile()) return false;
                System.out.println("Create new data file at " + dataFile.getAbsolutePath());
                writer.write("[]");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 销毁数据源文件
     */
    public static boolean destroy() {
        return dataFile.delete();
    }

    /**
     * 加载数据源文件中的数据
     *
     * @return 包含所有班级信息的列表。如果文件不存在，则返回空列表
     */
    public static ArrayList<Class> loadData() {
        try {
            return new Gson().fromJson(new FileReader(dataFile), new TypeToken<>() {
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 保存数据到数据源文件
     *
     * @param data 包含所有班级信息的列表
     */
    public static boolean saveData(List<Class> data) {
        try (FileWriter writer = new FileWriter(dataFile)) {
            new Gson().toJson(data, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}