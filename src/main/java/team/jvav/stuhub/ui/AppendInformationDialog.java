package team.jvav.stuhub.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppendInformationDialog extends JDialog {
    /**
     * 文本框，用于输入所加入得小组、学生
     */
    TextField group, student;

    /**
     * 提示信息
     */
    JLabel addGroup, addStudent;

    /**
     * 提交和取消按钮
     */
    JButton cancel, submit;

    /**
     * 构造方法，创建一个对话框
     *
     * @param frame 此对话框所依附的窗口
     * @param modal 设置是否必须关闭对话框才能返回父窗口，true为必须关闭才能返回
     */
    public AppendInformationDialog(JFrame frame, boolean modal) {
        super(frame, modal);

        // 父窗口四分之一大小
        setSize(frame.getSize().width / 2, frame.getSize().height / 2);

        // 设置居中于父窗口
        setLocationRelativeTo(null);

        // 两个JPanel容器，分别存放按钮和文本框
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // 设置提示内容
        addGroup = new JLabel("Add Group");
        addStudent = new JLabel("Add Student");
        panel.add(addGroup);
        panel.add(addStudent);

        // 设置文本框
        group = new TextField(10);
        panel.add(group);
        student = new TextField(10);
        panel.add(student);

        // 设置按钮
        cancel = new JButton("Cancel");
        buttonPanel.add(cancel);
        submit = new JButton("Submit");
        buttonPanel.add(submit);

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelAction();
            }
        });
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitAction();
            }
        });

        setVisible(true); // 设置可视
    }

    void submitAction() {
        group.getText();
    }

    void cancelAction() {
        setVisible(false);
    }
}
