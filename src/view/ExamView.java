package view;

import controller.ExamController;
import java.awt.ComponentOrientation;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * This class is the GUI which views the questions on screen
 */
public class ExamView extends javax.swing.JFrame {

    /**
     * Creates new form ExamView
     */
    private ExamController controller;

    public ExamView() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();
        questionText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new ExamKeyEventDispatcher());
        this.addKeyListener(new ExamViewListener());

    }

    public void setQuestionText(String text) {//set the text for current question on screen
        this.questionText.setText(text);
    }

    public void setOption1(String option) {//set the text for the first choice
        this.option1.setText(option);
    }

    public void setOption2(String option) {//set the text for the second choice
        this.option2.setText(option);
    }

    public void setOption3(String option) {//set the text for the third choice
        this.option3.setText(option);
    }

    public void setChoice(int choice) {//update the answer selected by the user
        switch (choice) {
            case 1:
                option1.setSelected(true);
                break;
            case 2:
                option2.setSelected(true);
                break;
            case 3:
                option3.setSelected(true);
                break;
            default:
                Options.clearSelection();
        }
    }

    public ExamController getController() {
        return controller;
    }

    public void setController(ExamController controller) {
        this.controller = controller;
    }

    private class ExamViewListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            int keyCode = ke.getKeyCode();
            switch (keyCode) {
                case ControlKeys.option1:
                    if (ke.isControlDown()) {
                        controller.readOption1();
                    } else {
                        controller.updateAnswer(1);
                        controller.readOption1();
                    }
                    break;
                case ControlKeys.option2:
                    if (ke.isControlDown()) {
                        controller.readOption2();
                    } else {
                        controller.updateAnswer(2);
                        controller.readOption2();
                    }
                    break;
                case ControlKeys.option3:
                    if (ke.isControlDown()) {
                        controller.readOption3();
                    } else {
                        controller.updateAnswer(3);
                        controller.readOption3();
                    }
                    break;
                case ControlKeys.readQuestion:
                    controller.readQuestion();
                    break;
                case ControlKeys.submit:
                    if (ke.isControlDown()) {
                        try {
                            controller.saveAttempt();
                            ExamView.this.dispose();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(rootPane, "حدث خطأ", "خطأ", JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(ExamView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            int keyCode = ke.getKeyCode();
            switch (keyCode) {
                case ControlKeys.nextKeyCode:
                    controller.nextQuestion();
                    break;
                case ControlKeys.previousKeyCode:
                    controller.prevoiusQuestion();
                    break;
                case ControlKeys.submit:
                    if (ke.isControlDown()) {
                        try {
                            controller.saveAttempt();
                            ExamView.this.dispose();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(rootPane, "حدث خطأ", "خطأ", JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(ExamView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Options = new javax.swing.ButtonGroup();
        option1 = new javax.swing.JRadioButton();
        option2 = new javax.swing.JRadioButton();
        option3 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        questionText = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Options.add(option1);
        option1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        option1.setText("الخيار الأول");
        option1.setFocusable(false);
        option1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        Options.add(option2);
        option2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        option2.setText("الخيار الثاني");
        option2.setFocusable(false);
        option2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        Options.add(option3);
        option3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        option3.setText("الخيار الثالث");
        option3.setFocusable(false);
        option3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        questionText.setEditable(false);
        questionText.setColumns(20);
        questionText.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        questionText.setLineWrap(true);
        questionText.setRows(5);
        questionText.setText("نص السؤال");
        questionText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        questionText.setEnabled(false);
        questionText.setFocusable(false);
        jScrollPane1.setViewportView(questionText);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("لاعتماد الاختبار والخروج اضغط Ctrl+Enter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(option3)
                    .addComponent(option1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(option2))
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(option1)
                .addGap(32, 32, 32)
                .addComponent(option2)
                .addGap(39, 39, 39)
                .addComponent(option3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Options;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton option1;
    private javax.swing.JRadioButton option2;
    private javax.swing.JRadioButton option3;
    private javax.swing.JTextArea questionText;
    // End of variables declaration//GEN-END:variables
}
