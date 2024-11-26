package team.jvav.stuhub.core.util;

import cn.hutool.poi.excel.ExcelWriter;
import team.jvav.stuhub.core.data.*;
import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.core.data.model.Group;

import java.util.ArrayList;

public class ExportScoreUtil {
    /**
     * 导出班级成绩的方法，将班级内所有小组成绩保存成Excel表格
     *
     * @param classId 导出成绩的班级的id
     * @param path    导出路径
     */
    public static void saveAsExcel(int classId, String path) {
        ArrayList<String> headers = new ArrayList<>();
        Class currentClass = DAO.getClassByID(classId);
        ArrayList<Group> groups = currentClass.getAllGroups();
        headers.add("小组号");
        headers.add("小组名");
        headers.add("小组成绩");

        ExcelWriter excelWriter = new ExcelWriter(path);
        excelWriter.writeHeadRow(headers);

        ArrayList<String> row = new ArrayList<>();
        for (Group group : groups) {
            String score = String.valueOf(group.getScore());
            String id = String.valueOf(group.getId());
            String groupName = group.getName();
            row.clear();
            row.add(id);
            row.add(groupName);
            row.add(score);
            excelWriter.writeRow(row);
        }
        excelWriter.close();
    }
}
