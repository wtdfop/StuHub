package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.swing.ui.constants.FrameConstants;

import javax.swing.*;
import java.awt.*;

public class ManageInformationFrame extends JFrame {
    /**
     * 父窗口
     */
    JFrame fatherFrame;

    /**
     * 跳转至抽取学生对话框的按钮和添加信息对话框的按钮
     */
    JButton drawButton, appendInformationButton;

    /**
     *
     */
    JDialog drawDialog, appendInformationDialog;

    /**
     * 构造一个新窗口，该窗口用于展示学生信息
     *
     * @param fatherFrame 父窗口
     */
    public ManageInformationFrame(JFrame fatherFrame) {
        super();

        // 隐藏上一个窗口
        this.fatherFrame = fatherFrame;
        fatherFrame.setVisible(false);

        // 窗口设置
        setBounds(FrameConstants.screenSize.height / 2, FrameConstants.screenSize.height / 2, FrameConstants.screenSize.width / 2, FrameConstants.screenSize.height / 2);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 按钮设置
        drawButton = new JButton("Draw");
        appendInformationButton = new JButton("Append Information");
        drawButton.addActionListener(e -> {
            drawDialog = new DrawDialog(this, true);
        });
        appendInformationButton.addActionListener(e -> {
            appendInformationDialog = new AppendInformationDialog(this, true);
        });

        // 布局设置
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 2));
        add(panel, BorderLayout.CENTER);
        panel.add(drawButton);
        panel.add(appendInformationButton);
        panel.setPreferredSize(new Dimension(FrameConstants.screenSize.width / 60, FrameConstants.screenSize.height / 60));

        // 设置窗口可见
        setVisible(true);
    }

    /**
     * @param operation the operation which should be performed when the
     *                  user closes the frame
     */
    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
        if (operation == JFrame.DISPOSE_ON_CLOSE) {
            fatherFrame.setVisible(true);
        }
    }
}
