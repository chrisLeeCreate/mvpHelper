import javax.swing.*;
import java.awt.event.*;

public class Hou extends JDialog implements ItemListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private DialogCallBack mCallBack;
    private boolean isActivity = true;
    private boolean isDagger = true;

    public Hou(DialogCallBack callBack) {
        this.mCallBack = callBack;
        setTitle("Mvp Create Helper");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(640, 360);
        setLocationRelativeTo(null);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        radioButton1.addItemListener(this);
        radioButton2.addItemListener(this);

        ButtonGroup daggerButtonGroup = new ButtonGroup();
        daggerButtonGroup.add(radioButton4);
        daggerButtonGroup.add(radioButton3);
        radioButton4.addItemListener(this);
        radioButton3.addItemListener(this);


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if (null != mCallBack) {
            mCallBack.ok(textField1.getText().trim(), textField2.getText().trim(), isActivity, isDagger);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == radioButton1) {
            isActivity = true;
        } else if (e.getSource() == radioButton2) {
            isActivity = false;
        } else if (e.getSource() == radioButton3) {
            isDagger = true;
        } else if (e.getSource() == radioButton4) {
            isDagger = false;
        }
    }

    public static void main(String[] args) {
        Hou dialog = new Hou(new DialogCallBack() {
            @Override
            public void ok(String author, String moduleName, boolean isActivity, boolean isDagger) {
                //                System.out.print("成功啦！！！！！！ " + "author:" + author + "moduleName" + moduleName);
            }
        });
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public interface DialogCallBack {
        void ok(String author, String moduleName, boolean isActivity, boolean isDagger);
    }

}
