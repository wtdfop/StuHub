package team.jvav.stuhub.swing.ui;

import team.jvav.stuhub.core.data.DAO;
import team.jvav.stuhub.core.data.model.Class;
import team.jvav.stuhub.core.data.model.Group;
import team.jvav.stuhub.core.data.model.Student;
import team.jvav.stuhub.core.util.RandomUtil;
import team.jvav.stuhub.swing.ui.constants.FrameConstants;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RandomStudentPanel extends JPanel {
    private JLabel lbl2 = new JLabel("学生姓名：");
    private JLabel lbl3 = new JLabel("学生照片：");
    private JLabel lblPic = new JLabel("照片");
    private JTextField txtStudent = new JTextField();
    private JButton btnChooseStudent = new JButton("随机学生");
    private JButton btnAbsence = new JButton("缺勤");
    private JButton btnAnswer = new JButton("答题");
    private Student currentStudent;
    Thread threadStudent = null;   // 随机抽取小组的线程

    public RandomStudentPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "随机学生点名"));
        this.setLayout(null);
        this.add(lbl2);
        this.add(lbl3);
        this.add(txtStudent);
        this.add(lblPic);
        this.add(btnChooseStudent);
        this.add(btnAbsence);
        this.add(btnAnswer);

        lbl2.setBounds(160, 50, 100, 30);
        txtStudent.setBounds(160, 90, 130, 30);
        txtStudent.setEditable(false);
        lblPic.setBounds(160, 130, 130, 150);
        btnChooseStudent.setBounds(160, 300, 130, 30);
        btnAbsence.setBounds(160, 340, 60, 30);
        btnAnswer.setBounds(300, 340, 60, 30);

        // 随机抽取学生
        btnChooseStudent.addActionListener(e -> {
            currentStudent = RandomUtil.getRandomStudentFromClass(FrameConstants.currentClassId);
            if (currentStudent == null) {
                JOptionPane.showMessageDialog(this, "当前班级没有学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                txtStudent.setText(currentStudent.getName());
            }
        });
        // 缺勤
        btnAbsence.addActionListener(e -> {
            Class currentClass = DAO.getClassByID(FrameConstants.currentClassId);
            Group belongingGroup = currentClass.getGroup(currentStudent.getBelongingGroupId());
            belongingGroup.subScore(FrameConstants.ABSENTEEISM_SCORE);
            DAO.updateGroupInClass(FrameConstants.currentClassId, belongingGroup);
            JOptionPane.showMessageDialog(this, "已记录缺勤", "", JOptionPane.INFORMATION_MESSAGE);
        });

        // 答题
        btnAnswer.addActionListener(e -> {
            if (txtStudent.getText() == null || txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Class currentClass = DAO.getClassByID(FrameConstants.currentClassId);
                Group belongingGroup = currentClass.getGroup(currentStudent.getBelongingGroupId());
                belongingGroup.addScore(FrameConstants.ANSWER_QUESTION);
                DAO.updateGroupInClass(FrameConstants.currentClassId, belongingGroup);
                JOptionPane.showMessageDialog(this, "回答正确", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
