package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClassAddPanel extends JPanel {
    public ClassAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增班级"));
        JLabel labelClassName = new JLabel("班级名称：");
        JTextField TextFieldClassName = new JTextField();
        JLabel labelClassNum = new JLabel("班级号：");
        JTextField TextFieldClassNum = new JTextField();
        JButton btnName = new JButton("确认");
        this.add(labelClassName);
        this.add(TextFieldClassName);
        this.add(labelClassNum);
        this.add(TextFieldClassNum);
        this.add(btnName);
        labelClassName.setBounds(200, 80, 100, 30);
        TextFieldClassName.setBounds(200, 130, 200, 30);
        labelClassNum.setBounds(200, 180, 100, 30);
        TextFieldClassNum.setBounds(200, 230, 200, 30);
        btnName.setBounds(200, 280, 100, 30);

        btnName.addActionListener(e -> {
            String className = TextFieldClassName.getText();
            String classNum = TextFieldClassNum.getText();
            if (className == null || className.isEmpty() || classNum == null || classNum.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写班级名称和班级号！", "", JOptionPane.INFORMATION_MESSAGE);
            } else if (!classNum.matches("^[1-9]\\d*$")) {
                JOptionPane.showMessageDialog(this, "班级号只能为正整数！", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (!DAO.createClass(Integer.parseInt(classNum), className)) {
                    JOptionPane.showMessageDialog(this, "已存在班级号相同的班级！", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "新增班级成功", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

}
