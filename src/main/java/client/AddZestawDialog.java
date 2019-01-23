package client;

import Repository.ZestawSprzetowRepo;
import model.SalaSportowa;
import model.ZestawSprzetow;

import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;

public class AddZestawDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField dyscyplinaTextField;
    private JList sprzetList;
    private JButton usunSprzetButton;
    private JButton edytujSprzetButton;
    private JLabel sprzetLabel;
    private JScrollPane scrollPaneList;
    private JPanel editPanel;
    private SalaSportowa salaSportowa;

    public AddZestawDialog(SalaSportowa salaSportowa, boolean isEdit) {
        this.salaSportowa = salaSportowa;

        if (isEdit) {
            editPanel.setVisible(true);
        } else {
            editPanel.setVisible(false);
        }
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
        usunSprzetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void onOK() {
        // add your code here
        String dyscyplinaText = dyscyplinaTextField.getText();
        if (dyscyplinaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ZestawSprzetow zestawSprzetow = new ZestawSprzetow(dyscyplinaText, Collections.emptyList(), salaSportowa);
        salaSportowa.addZestawSprzetow(zestawSprzetow);
        ZestawSprzetowRepo zestawSprzetowRepo = new ZestawSprzetowRepo();
        zestawSprzetowRepo.save(zestawSprzetow);

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
