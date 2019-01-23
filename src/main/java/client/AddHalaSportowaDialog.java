package client;

import Repository.SalaSportowaRepo;
import Repository.SzkolaRepo;
import lombok.extern.java.Log;
import model.SalaSportowa;
import model.Szkola;

import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;

@Log
public class AddHalaSportowaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField wielkoscTextField;
    private JComboBox szkolaComboBox;
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
        fillComboboxSzkola();
    }

    private void onOK() {
        // add your code here
        boolean checkBoxSelected = takCheckBox.isSelected();
        String wielkoscText = wielkoscTextField.getText();
        String szkolaText = szkolaComboBox.getSelectedItem().toString();
        if (wielkoscText.isEmpty() || szkolaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Szkola szkola = findSzkolaInDB(szkolaText);
        if (szkola == null) {
            JOptionPane.showMessageDialog(this, "Problem to connect and find szkola in DB!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Integer wielkosc = null;
        try {
            wielkosc = Integer.valueOf(wielkoscText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Wielkosc contains not only numbers!!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SalaSportowa salaSportowa = new SalaSportowa(wielkosc, checkBoxSelected, szkola, Collections.emptyList());
        SalaSportowaRepo salaSportowaRepo = new SalaSportowaRepo();
        salaSportowaRepo.save(salaSportowa);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
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

    private Szkola findSzkolaInDB(String selectedSzkola) {
        List<Szkola> szkolaList = new SzkolaRepo().getAll();
        for (int i = 0; i < szkolaList.size(); i++) {
            if (szkolaList.get(i).getNazwa().equals(selectedSzkola)) {
                return szkolaList.get(i);
            }
        }
        return null;
    }
}
