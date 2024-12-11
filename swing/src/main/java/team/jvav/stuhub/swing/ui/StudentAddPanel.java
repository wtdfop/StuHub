package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.swing.ui.constants.FrameConstants;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class StudentAddPanel extends JPanel {
    public StudentAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增学生"));
        JLabel lblId = new JLabel("学号：");
        JTextField txtId = new JTextField();
        JLabel lblName = new JLabel("姓名：");
        JTextField txtName = new JTextField();
        JLabel lblGroup = new JLabel("小组:");
        JComboBox<Group> cmbGroup = new JComboBox<>();
        JButton btnName = new JButton("确认");
        this.add(lblId);
        this.add(txtId);
        this.add(lblName);
        this.add(txtName);
        this.add(lblGroup);
        this.add(cmbGroup);
        this.add(btnName);
        lblId.setBounds(200, 60, 100, 30);
        txtId.setBounds(200, 100, 100, 30);
        lblName.setBounds(200, 140, 100, 30);
        txtName.setBounds(200, 180, 200, 30);
        lblGroup.setBounds(200, 220, 100, 30);
        cmbGroup.setBounds(200, 260, 100, 30);
        btnName.setBounds(200, 300, 100, 30);
        for (Group group : DAO.getClassByID(FrameConstants.currentClassId).getAllGroups()) {
            cmbGroup.addItem(group);
        }

        btnName.addActionListener(e -> {
            String id = txtId.getText();
            String name = txtName.getText();
            Group group = (Group) cmbGroup.getSelectedItem();

            if (id == null || id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写学号", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (!id.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "学号必须为整数", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写学生姓名", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (group == null) {
                JOptionPane.showMessageDialog(this, "请选择小组", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (DAO.createStudentInGroup(FrameConstants.currentClassId, group.getId(), Integer.parseInt(id), name)) {
                JOptionPane.showMessageDialog(this, "学生添加成功", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "学生添加失败", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
