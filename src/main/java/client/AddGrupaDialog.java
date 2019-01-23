package client;

import Repository.GrupaRepo;
import Repository.NauczycielRepo;
import Repository.SalaRepo;
import lombok.extern.java.Log;
import model.Grupa;
import model.Nauczyciel;
import model.Sala;

import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;

@Log
public class AddGrupaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nazwaTextField;
    private JTextField maxLiczbaTextField;
    private JTextField wiekTextField;
    private JComboBox nauczycielComboBox;
    private JComboBox salaComboBox;
    private JList uczniowieList;
    private List<Sala> salaList;
    private List<Nauczyciel> nauczycielList;

    public AddGrupaDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        fillNauczycielComboBox();
        fillSalaComboBox();
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
        String maxLiczbaText = maxLiczbaTextField.getText();
        String wiekText = wiekTextField.getText();
        String nauczycielCombo = nauczycielComboBox.getSelectedItem().toString();
        String salaCombo = salaComboBox.getSelectedItem().toString();
        if (nazwa.isEmpty() || maxLiczbaText.isEmpty() || wiekText.isEmpty() || nauczycielCombo.isEmpty() || salaCombo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Integer wiek = null;
        try {
            wiek = Integer.valueOf(wiekText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Wiek number contains not only numbers!!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Integer maxLiczba = null;
        try {
            maxLiczba = Integer.valueOf(maxLiczbaText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Max Liczba number contains not only numbers!!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Nauczyciel nauczyciel = findInNauczyciel(nauczycielCombo);
        if (nauczyciel == null) {
            log.info("Cannot found nauczyciel in list");
            return;
        }
        Sala sala = findInSalaList(salaCombo);
        if (sala == null) {
            log.info("Cannot found sala in list");
            return;
        }
        Grupa grupa = new Grupa(nazwa, maxLiczba, wiek, nauczyciel, sala, Collections.emptyList());
        GrupaRepo grupaRepo = new GrupaRepo();
        grupaRepo.save(grupa);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void fillNauczycielComboBox() {
        NauczycielRepo nauczycielRepo = new NauczycielRepo();
        nauczycielList = nauczycielRepo.getAll();
        nauczycielList.forEach(p -> nauczycielComboBox.addItem(p.getId() + " " + p.getImie() + " " + p.getNazwisko()));
    }

    private void fillSalaComboBox() {
        SalaRepo salaRepo = new SalaRepo();
        salaList = salaRepo.getAll();
        salaList.forEach(p -> salaComboBox.addItem(p.getNumerSali()));
    }

    private Sala findInSalaList(String toFind) {
        for (int i = 0; i < salaList.size(); i++) {
            if (salaList.get(i).getNumerSali().equals(toFind)) {
                return salaList.get(i);
            }
        }
        return null;
    }

    private Nauczyciel findInNauczyciel(String toFind) {
        String id = toFind.split(" ")[0];
        Long longID = Long.valueOf(id);
        for (int i = 0; i < nauczycielList.size(); i++) {
            if (nauczycielList.get(i).getId().equals(longID))
                return nauczycielList.get(i);
        }
        return null;
    }
}
