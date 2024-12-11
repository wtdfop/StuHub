package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.model.Class;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClassListPanel extends JPanel {
    String[] headers = {"班级号", "班级名称"};
    JTable classTable;
    JTextField txtName = new JTextField();
    JButton btnEdit = new JButton("修改");
    JButton btnDelete = new JButton("删除");
    private List<Class> classes;

    public ClassListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "班级列表"));
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
        txtName.setPreferredSize(new Dimension(256, 30));
        txtName.setText("选择一个班级来修改班级名称或者删除");
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        this.add(btnPanel, BorderLayout.SOUTH);

        classTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow >= 0) {
                txtName.setText(classes.get(selectedRow).getName());
            }
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (txtName.getText() == null || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写班级名称", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Class c = classes.get(selectedRow);
            c.setName(txtName.getText());
            DAO.updateClass(c);
            JOptionPane.showMessageDialog(this, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);
            refreshList();
        });
        btnDelete.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "删除班级会把小组、学生和成绩都删除，您确定要删除这个班级？", "", JOptionPane.YES_NO_OPTION) != 0) {
                return;
            }
            if (DAO.deleteClass(classes.get(selectedRow).getId())) {
                JOptionPane.showMessageDialog(this, "删除班级成功", "", JOptionPane.INFORMATION_MESSAGE);
                refreshList();
            }
        });
    }

    void refreshList() {
        classes = DAO.getAllClasses();
        String[][] data = new String[classes.size()][2];
        for (int i = 0; i < classes.size(); i++) {
            Class c = classes.get(i);
            data[i][0] = String.valueOf(c.getId());
            data[i][1] = c.getName();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        classTable.setModel(tableModel);
    }
}
