package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.core.data.model.Student;
import team.jvav.stuhub.swing.ui.constants.FrameConstants;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentListPanel extends JPanel {
    String[] headers = {"学号", "姓名", "小组"};
    String[][] data = null;
    JTable studentTable;
    JTextField txtName = new JTextField();
    JComboBox<Group> cmbGroup = new JComboBox<>();
    JButton btnEdit = new JButton("修改");
    JButton btnDelete = new JButton("删除");
    Class currentClass = DAO.getClassByID(FrameConstants.currentClassId);

    public StudentListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "学生列表"));
        this.setLayout(new BorderLayout());
        studentTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        this.add(scrollPane, BorderLayout.CENTER);

        refreshList();

        JPanel btnPanel = new JPanel();
        btnPanel.add(txtName);
        txtName.setPreferredSize(new Dimension(200, 30));
        btnPanel.add(cmbGroup);
        cmbGroup.setPreferredSize(new Dimension(100, 30));
        for (Group group : currentClass.getAllGroups()) {
            cmbGroup.addItem(group);
        }

        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        this.add(btnPanel, BorderLayout.SOUTH);

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                Student student = currentClass.getStudent(Integer.parseInt(data[selectedRow][0]));
                txtName.setText(student.getName());
                cmbGroup.setSelectedItem(currentClass.getGroup(student.getBelongingGroupId()));
            }
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择学生", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Student oldStudent = DAO.getStudentInClass(currentClass.getId(), Integer.parseInt(data[selectedRow][0]));

            String nameInput = txtName.getText();
            Group group = (Group) cmbGroup.getSelectedItem();
            if (nameInput == null || nameInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写姓名", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (group == null) {
                JOptionPane.showMessageDialog(this, "请选择小组", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            boolean modifySuccess = false;
            if (DAO.updateStudentInClass(currentClass.getId(), Integer.parseInt(data[selectedRow][0]), nameInput)) {
                modifySuccess = true;
            } else {
                System.out.println("update stu failed");
            }
            if (DAO.moveStudentToGroup(currentClass.getId(), Integer.parseInt(data[selectedRow][0]), oldStudent.getBelongingGroupId(), group.getId())) {
                modifySuccess = true;
            } else {
                System.out.println("move stu to group failed");
            }
            if (modifySuccess) {
                JOptionPane.showMessageDialog(this, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);
                refreshList();
            } else {
                JOptionPane.showMessageDialog(this, "修改失败", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnDelete.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择学生", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "删除学生会删除他的考勤、成绩等，确认删除？", "", JOptionPane.YES_NO_OPTION) != 0) {
                return;
            }
            Student student = currentClass.getStudent(Integer.parseInt(data[selectedRow][0]));
            if (DAO.deleteStudentInGroup(currentClass.getId(), student.getBelongingGroupId(), student.getId())) {
                JOptionPane.showMessageDialog(this, "删除学生成功", "", JOptionPane.INFORMATION_MESSAGE);
                refreshList();
            } else {
                JOptionPane.showMessageDialog(this, "删除学生失败", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    void refreshList() {
        if (currentClass == null) return;
        ArrayList<Student> students = currentClass.getAllStudents();
        data = new String[students.size()][3];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i] = new String[]{String.valueOf(student.getId()), student.getName(), currentClass.getGroup(student.getBelongingGroupId()).toString()};
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        studentTable.setModel(tableModel);
        txtName.setText("");
    }
}
