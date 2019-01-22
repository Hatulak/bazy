package client;

import Repository.MiastoRepo;
import Repository.NauczycielRepo;
import Repository.SzkolaRepo;
import lombok.extern.java.Log;
import model.Miasto;
import model.Nauczyciel;
import model.Szkola;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

@Log
public class AddNauczycielDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JTextField emailTextField;
    private JTextField telefonTextField;
    private JTextField stopienTextField;
    private JComboBox miastoComboBox;
    private JComboBox szkolaComboBox;
    private JTextField adresTextField;
    private JButton stworzMiastoButton;
    private List<Miasto> miastoList;
    private Nauczyciel editNauczyciel;
    public AddNauczycielDialog() {
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
        fillComboboxSzkola();

    }

    public AddNauczycielDialog(Nauczyciel nauczyciel) {
        this.editNauczyciel = nauczyciel;
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
        fillComboboxSzkola();
        imieTextField.setText(nauczyciel.getImie());
        nazwiskoTextField.setText(nauczyciel.getNazwisko());
        telefonTextField.setText(String.valueOf(nauczyciel.getTelefon()));
        emailTextField.setText(nauczyciel.getEmail());
        stopienTextField.setText(nauczyciel.getStopien());
        adresTextField.setText(nauczyciel.getAdres());
        miastoComboBox.setSelectedItem(nauczyciel.getMiasto().getNazwa());
        szkolaComboBox.setSelectedItem(nauczyciel.getSzkola().getNazwa());
    }

    private void onEditOK() {
        String imie = imieTextField.getText();
        String nazwisko = nazwiskoTextField.getText();
        String email = emailTextField.getText();
        String telefonString = telefonTextField.getText();
        String stopien = stopienTextField.getText();
        String adres = adresTextField.getText();
        String miastoCombo = miastoComboBox.getSelectedItem().toString();
        String szkolaCombo = szkolaComboBox.getSelectedItem().toString();
        if (imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || telefonString.isEmpty() || stopien.isEmpty() || adres.isEmpty() || miastoCombo.isEmpty() || szkolaCombo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Miasto miastoInList = MainClient.findMiastoInList(miastoCombo, miastoList);
        if (miastoInList == null) {
            JOptionPane.showMessageDialog(this, "Problem to connect miasto in DB!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Szkola szkolaInDB = findSzkolaInDB(szkolaCombo);
        if (szkolaInDB == null) {
            JOptionPane.showMessageDialog(this, "Problem to connect szkola in DB!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Integer telefon = null;
        try {
            telefon = Integer.valueOf(telefonString);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Phone number contains not only numbers!!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        NauczycielRepo nauczycielRepo = new NauczycielRepo();
        Nauczyciel nauczyciel = new Nauczyciel(imie, nazwisko, email, telefon, stopien, miastoInList, adres, szkolaInDB);
        nauczyciel.setId(editNauczyciel.getId());
        nauczycielRepo.update(nauczyciel);
        dispose();
    }

    private void fillComboboxSzkola() {
        SzkolaRepo szkolaRepo = new SzkolaRepo();
        List<Szkola> all = szkolaRepo.getAll();
        if (all == null) {
            log.info("There is no szkola in DB");
            JOptionPane.showMessageDialog(this, "There is no szkola in DB!! or problem with database", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        all.forEach(p -> szkolaComboBox.addItem(p.getNazwa()));
    }

    private void onOK() {
        // add your code here
        String imie = imieTextField.getText();
        String nazwisko = nazwiskoTextField.getText();
        String email = emailTextField.getText();
        String telefonString = telefonTextField.getText();
        String stopien = stopienTextField.getText();
        String adres = adresTextField.getText();
        String miastoCombo = miastoComboBox.getSelectedItem().toString();
        String szkolaCombo = szkolaComboBox.getSelectedItem().toString();
        if (imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || telefonString.isEmpty() || stopien.isEmpty() || adres.isEmpty() || miastoCombo.isEmpty() || szkolaCombo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Miasto miastoInList = MainClient.findMiastoInList(miastoCombo, miastoList);
        if (miastoInList == null) {
            JOptionPane.showMessageDialog(this, "Problem to connect miasto in DB!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Szkola szkolaInDB = findSzkolaInDB(szkolaCombo);
        if (szkolaInDB == null) {
            JOptionPane.showMessageDialog(this, "Problem to connect szkola in DB!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Integer telefon = null;
        try {
            telefon = Integer.valueOf(telefonString);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Phone number contains not only numbers!!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Nauczyciel nauczyciel = new Nauczyciel(imie, nazwisko, email, telefon, stopien, miastoInList, adres, szkolaInDB);
        NauczycielRepo nauczycielRepo = new NauczycielRepo();
        nauczycielRepo.save(nauczyciel);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private static Szkola findSzkolaInDB(String selectedSzkola) {
        List<Szkola> szkolaList = new SzkolaRepo().getAll();
        for (int i = 0; i < szkolaList.size(); i++) {
            if (szkolaList.get(i).getNazwa().equals(selectedSzkola)) {
                return szkolaList.get(i);
            }
        }
        return null;
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
}
