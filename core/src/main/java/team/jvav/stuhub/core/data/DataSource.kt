package team.jvav.stuhub.core.data

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import team.jvav.stuhub.core.data.model.Class
import java.io.File

/**
 * 数据源类，负责管理数据源文件
 *
 * 若要进行班级、小组和学生的具体的增删查改操作，请使用 DAO 类
 */
object DataSource {
    /**
     * 数据源文件
     */
    val dataFile = File("StuHubData/data.json")

    /**
     * 检查数据源文件是否存在
     */
    fun exists() = dataFile.exists()

    /**
     * 初始化数据源文件。如果文件不存在，则创建新文件并写入空列表
     */
    fun init() {
        if (!dataFile.exists()) {
            dataFile.parentFile.mkdirs()
            dataFile.createNewFile()
            println("Create new data file at ${dataFile.absolutePath}")
            dataFile.writeText("[]")
        }
    }

    /**
     * 销毁数据源文件
     */
    fun destroy() = dataFile.delete()

    /**
     * 加载数据源文件中的数据
     * @return 包含所有班级信息的列表
     */
    fun loadData(): MutableList<Class> = Json.decodeFromString(dataFile.readText())

    /**
     * 保存数据到数据源文件
     * @param data 包含所有班级信息的列表
     */
    fun saveData(data: List<Class>) = dataFile.writeText(Json.encodeToString(data))
}