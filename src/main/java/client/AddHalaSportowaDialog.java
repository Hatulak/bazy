package client;

import javax.swing.*;
import java.awt.event.*;

public class AddHalaSportowaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField wielkoscTextField;
    private JComboBox szkolaComboBox;
    private JList zestawyList;
    private JCheckBox takCheckBox;
    private JButton stworzButton;

    public AddHalaSportowaDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        stworzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddZestawDialog addZestawDialog = new AddZestawDialog();
                addZestawDialog.pack();
                addZestawDialog.setVisible(true);
            }
        });
    }

    private void onOK() {
        // add your code here
        String wielkosc = wielkoscTextField.getText();
        String szkola = szkolaComboBox.getSelectedItem().toString();

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
