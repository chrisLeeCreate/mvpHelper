import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class DaggerDialog extends JDialog implements ItemListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton daggerRadioButton;
    private JRadioButton noneDaggerRadioButton;
    private JRadioButton activityRadioButton;
    private JRadioButton fragmentRadioButton;

    private DialogCallBack mCallBack;
    private boolean isActivity = true;
    private boolean isDagger = true;

    public DaggerDialog(DialogCallBack callBack) {
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
        buttonGroup.add(activityRadioButton);
        buttonGroup.add(fragmentRadioButton);
        activityRadioButton.addItemListener(this);
        fragmentRadioButton.addItemListener(this);

        ButtonGroup daggerButtonGroup = new ButtonGroup();
        daggerButtonGroup.add(daggerRadioButton);
        daggerButtonGroup.add(noneDaggerRadioButton);
        daggerRadioButton.addItemListener(this);
        noneDaggerRadioButton.addItemListener(this);


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
        if (e.getSource() == activityRadioButton) {
            isActivity = true;
        } else if (e.getSource() == fragmentRadioButton) {
            isActivity = false;
        } else if (e.getSource() == daggerRadioButton) {
            isDagger = true;
        } else if (e.getSource() == noneDaggerRadioButton) {
            isDagger = false;
        }
    }

    public static void main(String[] args) {
        DaggerDialog dialog = new DaggerDialog(new DialogCallBack() {
            @Override
            public void ok(String author, String moduleName, boolean isActivity, boolean isDagger) {

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
