package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.swing.ui.constants.FrameConstants;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.util.List;

public class ChangeClassPanel extends JScrollPane {
    JLabel infoLbl = new JLabel();
    JButton okBtn = new JButton("确定");

    public ChangeClassPanel(MainFrame mainFrame) {
        this.setBorder(new TitledBorder(new EtchedBorder(), "新选择班级"));
        int x = 160, y = 100;
        this.setLayout(null);
        // 读取目录获取班级
        List<Class> classes = DAO.getAllClasses();
        if (classes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请先创建班级", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ButtonGroup btnGroup = new ButtonGroup();
            for (Class c : classes) {
                JRadioButton classRadio = new JRadioButton(c.getName() + " - (" + c.getId() + ")");
                classRadio.addActionListener(e -> FrameConstants.currentClassId = c.getId());
                if (FrameConstants.currentClassId == c.getId()) {
                    classRadio.setSelected(true);
                }
                btnGroup.add(classRadio);
                this.add(classRadio);
                classRadio.setBounds(x, y, 200, 30);
                y += 40;
            }
            this.add(okBtn);
            okBtn.setBounds(x, 800, 100, 30);

            this.repaint();
            this.validate();

            okBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "已选择班级：" + FrameConstants.currentClassId, "", JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }
}
