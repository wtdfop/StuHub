package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.swing.ui.constants.FrameConstants;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GroupListPanel extends JPanel {
    String[] headers = {"组号", "组名", "分数"};
    String[][] data = null;
    JTable classTable;
    JTextField txtName = new JTextField();
    JTextField txtScore = new JTextField();
    JButton btnEdit = new JButton("修改");
    JButton btnDelete = new JButton("删除");

    public GroupListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "小组列表"));
        this.setLayout(new BorderLayout());
        classTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        classTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(classTable);
        this.add(scrollPane, BorderLayout.CENTER);

        refreshList();

        JPanel btnPanel = new JPanel();
        btnPanel.add(txtName);
        txtName.setPreferredSize(new Dimension(200, 30));
        btnPanel.add(txtScore);
        txtScore.setPreferredSize(new Dimension(100, 30));
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        this.add(btnPanel, BorderLayout.SOUTH);
        txtName.setText("组名");
        txtScore.setText("分数");

        classTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow < 0) {
                return;
            }
            Group group = DAO.getGroupByIDInClass(FrameConstants.currentClassId, Integer.parseInt(data[selectedRow][0]));
            if (group != null) {
                txtName.setText(group.getName());
                txtScore.setText(String.valueOf(group.getScore()));
            }
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow < 0) {
                return;
            }
            String nameInput = txtName.getText();
            String scoreInput = txtScore.getText();
            if (nameInput.isEmpty() || scoreInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "组名或分数不能为空", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!scoreInput.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "分数必须为整数", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int score = Integer.parseInt(scoreInput);
            Group group = DAO.getGroupByIDInClass(FrameConstants.currentClassId, Integer.parseInt(data[selectedRow][0]));
            if (group != null) {
                group.setName(nameInput);
                group.setScore(score);
                if (DAO.updateGroupInClass(FrameConstants.currentClassId, group)) {
                    JOptionPane.showMessageDialog(this, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);
                    refreshList();
                } else {
                    JOptionPane.showMessageDialog(this, "修改失败", "", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "修改失败", "", JOptionPane.ERROR_MESSAGE);
            }
            txtName.setText("组名");
            txtScore.setText("分数");
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow < 0) {
                return;
            }
            Group group = DAO.getGroupByIDInClass(FrameConstants.currentClassId, Integer.parseInt(data[selectedRow][0]));
            if (group != null && DAO.deleteGroupInClass(FrameConstants.currentClassId, group.getId())) {
                JOptionPane.showMessageDialog(this, "删除成功", "", JOptionPane.INFORMATION_MESSAGE);
                refreshList();
            } else {
                JOptionPane.showMessageDialog(this, "删除失败", "", JOptionPane.ERROR_MESSAGE);
            }
            txtName.setText("组名");
            txtScore.setText("分数");
        });
    }

    void refreshList() {
        Class clazz = DAO.getClassByID(FrameConstants.currentClassId);
        if (clazz == null) {
            JOptionPane.showMessageDialog(this, "当前班级不存在，请重新选择班级", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Group> groups = clazz.getAllGroups();
        data = new String[groups.size()][3];
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            data[i][0] = String.valueOf(group.getId());
            data[i][1] = group.getName();
            data[i][2] = String.valueOf(group.getScore());
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        classTable.setModel(tableModel);
    }
}
