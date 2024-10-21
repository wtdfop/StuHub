package team.jvav.stuhub.ui;

import javax.swing.*;
import java.awt.*;
import team.jvav.stuhub.data.constants.*;

/**
 * MainFrame类为主窗口，包含主要功能和组件
 */
public class MainFrame extends JFrame {
    /**
     * 主窗口欢迎语
     */
    JLabel nameLabel = new JLabel();

    /**
     * 窗口跳转按钮
     */
    JButton manageButton = new JButton("Manage students");
    JButton ShowButton = new JButton("Show students and group");

    /**
     * 构造方法
     */
    public MainFrame() {
        super("StuHub");

        // 设置主窗口布局
        setLayout(new BorderLayout());
        setBounds(FrameConstants.screenSize.height / 2, FrameConstants.screenSize.height / 2, FrameConstants.screenSize.width / 2, FrameConstants.screenSize.height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建按钮容器
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(manageButton);
        buttonPanel.add(ShowButton);

        // 设置按钮的监听事件
        manageButton.addActionListener(e -> {
            new ManageInformationFrame(this);
        });
        ShowButton.addActionListener(e -> {

        });
//        manageButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                 new ShowFunctionFrame(this);
//            }
//        });
//        ShowButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });

        // 设置按钮和欢迎词的位置
        add(buttonPanel, BorderLayout.SOUTH);
        add(nameLabel, BorderLayout.CENTER);

        // 其他设置
        setVisible(true);
    }
}
