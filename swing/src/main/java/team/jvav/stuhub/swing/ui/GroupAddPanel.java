package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.swing.ui.constants.FrameConstants;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GroupAddPanel extends JPanel {
    public GroupAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增小组"));
        JLabel lblName = new JLabel("组名：");
        JLabel lblNumber = new JLabel("组号：");
        JTextField txtName = new JTextField();
        JTextField txtNumber = new JTextField();
        JButton btnName = new JButton("确认");
        this.add(lblName);
        this.add(txtName);
        this.add(lblNumber);
        this.add(txtNumber);
        this.add(btnName);
        lblName.setBounds(200, 80, 100, 30);
        txtName.setBounds(200, 130, 200, 30);
        lblNumber.setBounds(200, 180, 100, 30);
        txtNumber.setBounds(200, 230, 200, 30);
        btnName.setBounds(200, 280, 100, 30);

        btnName.addActionListener(e -> {
            String nameInput = txtName.getText(), numberInput = txtNumber.getText();
            if (nameInput == null || nameInput.isEmpty() || numberInput == null || numberInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写组名和组号！", "", JOptionPane.INFORMATION_MESSAGE);
            } else if (!numberInput.matches("^[1-9]\\d*$")) {
                JOptionPane.showMessageDialog(this, "组号只能为正整数！", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String name = txtName.getText();
                int id = Integer.parseInt(txtNumber.getText());

                if (DAO.createGroupInClass(FrameConstants.currentClassId, id, name)) {
                    JOptionPane.showMessageDialog(this, "新增小组成功", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "新增小组失败！可能是因为已经存在同组号的小组！", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

}
