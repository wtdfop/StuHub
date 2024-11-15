package team.jvav.stuhub.swing.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 新增班级对话框类
 *
 * @author [你的名字]
 * @version 1.0
 * @since 2024-01-01
 */
public class AddClassDialog extends JDialog {
    /** 班级名称输入框 */
    private JTextField classNameField;

    /** 新增按钮 */
    private JButton addButton;

    /** 返回按钮 */
    private JButton cancelButton;

    /**
     * 新增班级对话框构造函数
     *
     * @param owner 父窗口
     */
    public AddClassDialog(Frame owner) {
        super(owner, "新增班级", true);
        setSize(300, 200);
        setLocationRelativeTo(owner);

        initComponents();
        layoutComponents();
    }

    /**
     * 初始化对话框组件
     * 包括输入框和按钮的创建与配置
     */
    private void initComponents() {
        classNameField = new JTextField(20);
        addButton = new JButton("新增");
        cancelButton = new JButton("返回");

        // 新增按钮点击事件
        addButton.addActionListener(e -> {
            String className = classNameField.getText().trim();
            if (!className.isEmpty()) {
                showSuccessStatus();
            }
        });

        // 返回按钮点击事件
        cancelButton.addActionListener(e -> dispose());
    }

    /**
     * 显示新增成功状态
     */
    private void showSuccessStatus() {
        JLabel successLabel = new JLabel("新增成功", SwingConstants.CENTER);
        successLabel.setForeground(Color.RED);
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(successLabel, BorderLayout.CENTER);
        getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    /**
     * 设置对话框组件的布局
     */
    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // 创建标题面板
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("新增班级"));

        // 创建输入面板
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(new JLabel("班级名称:"));
        inputPanel.add(classNameField);

        // 创建按钮面板
        JPanel buttonPanel = createButtonPanel();

        // 添加到主面板
        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * 创建按钮面板
     *
     * @return 包含按钮的面板
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }
}