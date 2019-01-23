package client;

import Repository.MiastoRepo;
import Repository.RodzicRepo;
import lombok.extern.java.Log;
import model.Miasto;
import model.Rodzic;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

import static client.MainClient.findMiastoInList;

@Log
public class AddRodzicDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JComboBox miastoComboBox;
    private JTextField adresTextField;
    private JTextField telefonTextField;
    private JButton stworzMiastoButton;

    public AddRodzicDialog() {
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
        stworzMiastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMiastoDialog addMiastoDialog = new AddMiastoDialog();
                addMiastoDialog.pack();
                addMiastoDialog.setVisible(true);
                fillComboboxMiasto();
            }
        });
        fillComboboxMiasto();
    }

    private void fillComboboxMiasto() {
        miastoComboBox.removeAllItems();
        MiastoRepo miastoRepo = new MiastoRepo();
        List<Miasto> miastoList = miastoRepo.getAll();
        if (miastoList == null) {
            log.info("Empty list with miasto's get from DB");
        } else {
            miastoList.forEach(p -> miastoComboBox.addItem(p.getNazwa()));
        }
    }

    private void onOK() {
        miastoComboBox.removeAllItems();
        MiastoRepo miastoRepo = new MiastoRepo();
        List<Miasto> miastoList = miastoRepo.getAll();
        if (miastoList == null) {
            log.info("Empty list with miasto's get from DB");
        } else {
            miastoList.forEach(p -> miastoComboBox.addItem(p.getNazwa()));
        }
        String miastoComboString = miastoComboBox.getSelectedItem().toString();
        if (miastoComboString.isEmpty()) {
            return;
        }
        Miasto miastoInList = findMiastoInList(miastoComboString, miastoList);
        String imie = imieTextField.getText();
        String nazwisko = nazwiskoTextField.getText();
        String adres = adresTextField.getText();
        Integer telefon = null;
        try {
            telefon = Integer.parseInt(telefonTextField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Phone number contains not only numbers!!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Rodzic rodzic = new Rodzic(imie, nazwisko, miastoInList, adres, telefon);
        RodzicRepo rodzicRepo = new RodzicRepo();
        rodzicRepo.save(rodzic);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
