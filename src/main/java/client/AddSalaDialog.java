package client;

import javax.swing.*;
import java.awt.event.*;

public class AddSalaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField numerTextField;
    private JTextField liczbaKrzeselTextField;
    private JTextField liczbaLawekTextField;
    private JComboBox szkolaComboBox;
    private JComboBox rzutnikComboBox;
    private JList komputeryList;
    private JButton stworzRzutnikButton;
    private JButton stworzKomputerButton;

    public AddSalaDialog() {
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
        stworzRzutnikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRzutnikDialog addRzutnikDialog = new AddRzutnikDialog();
                addRzutnikDialog.pack();
                addRzutnikDialog.setVisible(true);
            }
        });
        stworzKomputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddKomputerDialog addKomputerDialog = new AddKomputerDialog();
                addKomputerDialog.pack();
                addKomputerDialog.setVisible(true);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
