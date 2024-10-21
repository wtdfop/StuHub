package team.jvav.stuhub.ui;

import team.jvav.stuhub.Main;
import team.jvav.stuhub.data.model.Group;
import team.jvav.stuhub.data.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.jvav.stuhub.data.constants.*;

public class DrawDialog extends JDialog {
    /**
     * 在班级内抽取学生的按钮、抽取小组的按钮、在小组内抽取学生的按钮
     */
    public JButton drawStudentFromClassButton, drawGroupFromClassButton, drawStudentFromGroupButton;

    /**
     * 面板容器
     */
    Container contentPane;

    /**
     * 从班级抽取学生的显示结果、从小组抽取学生的显示结果、抽取小组的显示结果
     */
    JLabel classStudentLabel, groupStudentLabel, groupLabel;

    /**
     * 构造方法，构造一个抽取学生和小组的窗口
     *
     * @param frame 设置其父窗口
     * @param modal 设置其模态
     */
    public DrawDialog(JFrame frame, boolean modal) {
        super(frame, modal);

        drawStudentFromClassButton = new JButton("Draw Student From Class");
        drawStudentFromClassButton.setSize(200, 50);
        contentPane.add(drawStudentFromClassButton);

        drawGroupFromClassButton = new JButton("Draw Group From Class");
        drawGroupFromClassButton.setBounds(400, 150, 200, 50);
        contentPane.add(drawGroupFromClassButton);

        drawStudentFromGroupButton = new JButton("Draw Student From Group");
        drawStudentFromGroupButton.setBounds(400, 250, 200, 50);
        contentPane.add(drawStudentFromGroupButton);

        // 窗口大小位置设置
        setSize(FrameConstants.screenSize.width / 2, FrameConstants.screenSize.height / 2);   // 根据屏幕尺寸设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        drawStudentFromClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student t = Main.c.getRandomStudent();
                if (classStudentLabel != null) {
                    contentPane.remove(classStudentLabel);
                }
                classStudentLabel = new JLabel(t.getName());
                classStudentLabel.setBounds(300, 50, 100, 50);
                contentPane.add(classStudentLabel);
                contentPane.revalidate(); // 更新布局
                contentPane.repaint(); // 重绘内容面板
            }
        });

        drawGroupFromClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Group t = Main.c.getRandomGroup();
                if (groupLabel != null) {
                    contentPane.remove(groupLabel);
                }
                groupLabel = new JLabel(t.getName());
                groupLabel.setBounds(300, 150, 100, 50);
                contentPane.add(groupLabel);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        drawStudentFromGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student t = Main.c.getGroup(2).getRandomStudent();
                if (groupStudentLabel != null) {
                    contentPane.remove(groupStudentLabel);
                }
                groupStudentLabel = new JLabel(t.getName());
                groupStudentLabel.setBounds(300, 250, 100, 50);
                contentPane.add(groupStudentLabel);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
    }
}
