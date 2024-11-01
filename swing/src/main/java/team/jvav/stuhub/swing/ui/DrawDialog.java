package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.core.data.model.Student;
import team.jvav.stuhub.core.util.RandomUtil;
import team.jvav.stuhub.swing.ui.constants.FrameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawDialog extends JDialog {
    /**
     * 在班级内抽取学生的按钮、抽取小组的按钮、在小组内抽取学生的按钮
     */
    public JButton drawStudentFromClassButton, drawGroupFromClassButton, drawStudentFromGroupButton;

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

        setLayout(null);

        // 按钮大小设置
        drawStudentFromClassButton = new JButton("Draw Student From Class");
        drawStudentFromClassButton.setBounds(400, 50, 200, 50);

        drawGroupFromClassButton = new JButton("Draw Group From Class");
        drawGroupFromClassButton.setBounds(400, 150, 200, 50);

        drawStudentFromGroupButton = new JButton("Draw Student From Group");
        drawStudentFromGroupButton.setBounds(400, 250, 200, 50);

        add(drawStudentFromClassButton);
        add(drawGroupFromClassButton);
        add(drawStudentFromGroupButton);

        // 对话框大小位置设置
        setSize(FrameConstants.screenSize.width / 2, FrameConstants.screenSize.height / 2);   // 根据屏幕尺寸设置窗口大小
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        drawStudentFromClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student t = RandomUtil.INSTANCE.getRandomStudentFromClass(1);
                if (classStudentLabel != null) {
                    remove(classStudentLabel);
                }
                classStudentLabel = new JLabel(t.getName());
                classStudentLabel.setBounds(300, 50, 100, 50);
                add(classStudentLabel);
                revalidate(); // 更新布局
                repaint(); // 重绘内容面板
            }
        });

        drawGroupFromClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Group t = RandomUtil.INSTANCE.getRandomGroupFromClass(1);
                if (groupLabel != null) {
                    remove(groupLabel);
                }
                groupLabel = new JLabel(t.getName());
                groupLabel.setBounds(300, 150, 100, 50);
                add(groupLabel);
                revalidate();
                repaint();
            }
        });

        drawStudentFromGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student t = RandomUtil.INSTANCE.getRandomStudentFromGroup(1, DAO.INSTANCE.getGroupsInClass(1).get(2).getId());
                if (groupStudentLabel != null) {
                    remove(groupStudentLabel);
                }
                groupStudentLabel = new JLabel(t.getName());
                groupStudentLabel.setBounds(300, 250, 100, 50);
                add(groupStudentLabel);
                revalidate();
                repaint();
            }
        });

        setVisible(true);
    }
}
