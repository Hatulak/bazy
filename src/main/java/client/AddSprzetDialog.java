package client;

import Repository.SprzetRepo;
import model.Sprzet;

import javax.swing.*;
import java.awt.event.*;

public class AddSprzetDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nazwaTextField;
    private JTextField iloscTextField;
    private JComboBox zestawComboBox;

    public AddSprzetDialog() {
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
    }

    private void onOK() {
        // add your code here
        String nazwa = nazwaTextField.getText();
        String iloscString = iloscTextField.getText();
        if (nazwa.isEmpty() || iloscString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Integer ilosc = null;
        try {
            ilosc = Integer.valueOf(iloscString);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Phone number contains not only numbers!!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SprzetRepo sprzetRepo = new SprzetRepo();
        //fixme need to change after add combobox :3


        sprzetRepo.save(new Sprzet(nazwa, ilosc, null));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
