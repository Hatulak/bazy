package client;

import Repository.MiastoRepo;
import Repository.SzkolaRepo;
import lombok.extern.java.Log;
import model.Miasto;
import model.Szkola;

import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;

@Log
public class AddSzkolaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox miastoComboBox;
    private JTextField nazwaTextField;
    private JTextField patronTextField;
    private JTextField adresTextField;
    private JButton stworzMiastoButton;
    private List<Miasto> miastoList;

    private Szkola szkola;

    public AddSzkolaDialog() {
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
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        stworzMiastoButton.addActionListener(e -> {
            AddMiastoDialog addMiastoDialog = new AddMiastoDialog();
            addMiastoDialog.pack();
            addMiastoDialog.setVisible(true);
            fillComboboxMiasto();
        });
        fillComboboxMiasto();
    }

    public AddSzkolaDialog(Szkola szkola) {
        this.szkola = szkola;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEditOK();
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
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        stworzMiastoButton.addActionListener(e -> {
            AddMiastoDialog addMiastoDialog = new AddMiastoDialog();
            addMiastoDialog.pack();
            addMiastoDialog.setVisible(true);
            fillComboboxMiasto();
        });
        fillComboboxMiasto();

        nazwaTextField.setText(szkola.getNazwa());
        patronTextField.setText(szkola.getPatron());
        adresTextField.setText(szkola.getAdres());
        miastoComboBox.setSelectedItem(szkola.getMiasto().getNazwa());
    }

    private void fillComboboxMiasto() {
        miastoComboBox.removeAllItems();
        MiastoRepo miastoRepo = new MiastoRepo();
        miastoList = miastoRepo.getAll();
        if (miastoList == null) {
            log.info("Empty list with miasto's get from DB");
        } else {
            miastoList.forEach(p -> miastoComboBox.addItem(p.getNazwa()));
        }
    }

    private void onEditOK() {
        // add your code here
        String nazwa = nazwaTextField.getText();
        String patron = patronTextField.getText();
        String adres = adresTextField.getText();
        String miasto = miastoComboBox.getSelectedItem().toString();
        if (miasto.isEmpty() || nazwa.isEmpty() || patron.isEmpty() || adres.isEmpty()) {
            log.info("Empty field");
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Miasto miastoInList = MainClient.findMiastoInList(miasto, miastoList);
        if (miastoInList == null) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        szkola.setNazwa(nazwa);
        szkola.setPatron(patron);
        szkola.setAdres(adres);
        szkola.setMiasto(miastoInList);

        SzkolaRepo szkolaRepo = new SzkolaRepo();
        szkolaRepo.update(szkola);
        dispose();
    }

    private void onOK() {
        // add your code here
        String nazwa = nazwaTextField.getText();
        String patron = patronTextField.getText();
        String adres = adresTextField.getText();
        String miasto = miastoComboBox.getSelectedItem().toString();
        if (miasto.isEmpty() || nazwa.isEmpty() || patron.isEmpty() || adres.isEmpty()) {
            log.info("Empty field");
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Miasto miastoInList = MainClient.findMiastoInList(miasto, miastoList);
        if (miastoInList == null) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SzkolaRepo szkolaRepo = new SzkolaRepo();
        szkolaRepo.save(new Szkola(miastoInList, nazwa, patron, adres, Collections.emptyList(), Collections.emptyList(), Collections.emptyList()));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
