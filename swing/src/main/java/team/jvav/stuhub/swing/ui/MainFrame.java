package team.jvav.stuhub.swing.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 课堂随机点名系统的主窗口类
 *
 * @author [你的名字]
 * @version 1.0
 * @since 2024-01-01
 */
public class MainFrame extends JFrame {
    /** 菜单栏组件 */
    private JMenuBar menuBar;

    /** 内容面板 */
    private JPanel contentPanel;

    /** 菜单项文本数组 */
    private final String[] menuItems = {"文件", "班级管理", "小组管理", "学生管理", "课堂管理"};

    /**
     * 主窗口构造函数
     * 初始化窗口属性并设置布局
     */
    public MainFrame() {
        setTitle("课堂随机点名系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        layoutComponents();

        setLocationRelativeTo(null);
    }

    /**
     * 初始化窗口组件
     * 包括菜单栏和内容面板的创建与配置
     */
    private void initComponents() {
        // 初始化菜单栏
        menuBar = new JMenuBar();
        for (String menuText : menuItems) {
            JMenu menu = new JMenu(menuText);
            // 根据不同的菜单类型添加对应的子菜单项
            switch (menuText) {
                case "班级管理":
                    menu.add(createMenuItem("新增班级"));
                    menu.add(createMenuItem("班级列表"));
                    break;
                case "小组管理":
                    menu.add(createMenuItem("新增小组"));
                    menu.add(createMenuItem("小组列表"));
                    break;
                case "学生管理":
                    menu.add(createMenuItem("新增学生"));
                    menu.add(createMenuItem("学生列表"));
                    break;
                case "课堂管理":
                    menu.add(createMenuItem("随机小组"));
                    menu.add(createMenuItem("随机学生"));
                    break;
                default:
                    break;
            }
            menuBar.add(menu);
        }

        // 初始化内容面板
        contentPanel = new JPanel(new BorderLayout());
        JLabel promptLabel = new JLabel("请选择班级", SwingConstants.CENTER);
        promptLabel.setForeground(Color.RED);
        promptLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        contentPanel.add(promptLabel, BorderLayout.CENTER);
    }

    /**
     * 创建菜单项并添加事件监听器
     *
     * @param text 菜单项显示的文本
     * @return 创建好的菜单项
     */
    private JMenuItem createMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(e -> {
            if ("新增班级".equals(text)) {
                showAddClassDialog();
            }
            // 其他菜单项的处理逻辑可以在这里添加
        });
        return menuItem;
    }

    /**
     * 设置窗口组件的布局
     */
    private void layoutComponents() {
        setJMenuBar(menuBar);
        add(contentPanel);
    }

    /**
     * 显示新增班级对话框
     */
    private void showAddClassDialog() {
        AddClassDialog dialog = new AddClassDialog(this);
        dialog.setVisible(true);
    }

    /**
     * 程序入口方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainFrame().setVisible(true);
        });
    }
}