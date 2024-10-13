package team.jvav.stuhub.data.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.jvav.stuhub.Main;

public class MainFrame extends JFrame {
    public JButton drawStudentFromClassButton, drawGroupFromClassButton, drawStudentFromGroupButton;
    Container contentPane;
    JLabel classStudentLabel, groupStudentLabel, groupLabel;

    public MainFrame() {
        super("StuHub");

        contentPane = getContentPane(); // 使用 'getContentPane()' 获取内容面板
        contentPane.setLayout(null);

        drawStudentFromClassButton = new JButton("Draw Student From Class");
        drawStudentFromClassButton.setBounds(400, 50, 200, 50);
        contentPane.add(drawStudentFromClassButton);

        drawGroupFromClassButton = new JButton("Draw Group From Class");
        drawGroupFromClassButton.setBounds(400, 150, 200, 50);
        contentPane.add(drawGroupFromClassButton);

        drawStudentFromGroupButton = new JButton("Draw Student From Group");
        drawStudentFromGroupButton.setBounds(400, 250, 200, 50);
        contentPane.add(drawStudentFromGroupButton);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // 使用 Toolkit 获取屏幕尺寸
        setSize(size.width / 2, size.height / 2);
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
                Student t = Main.c.getRandomStudentFromGroup(2);
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
