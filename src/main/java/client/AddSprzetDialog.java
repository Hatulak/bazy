package client;

import Repository.SprzetRepo;
import Repository.ZestawSprzetowRepo;
import model.Sprzet;
import model.ZestawSprzetow;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddSprzetDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nazwaTextField;
    private JTextField iloscTextField;
    private JComboBox zestawComboBox;
    private List<ZestawSprzetow> zestawSprzetowList;

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

        fillZestawComboBox();
    }

    private void onOK() {
        // add your code here
        String nazwa = nazwaTextField.getText();
        String iloscString = iloscTextField.getText();
        String zestawCombo = zestawComboBox.getSelectedItem().toString();
        if (nazwa.isEmpty() || iloscString.isEmpty() || zestawCombo.isEmpty()) {
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
        ZestawSprzetow zestawSprzetow = findInList(zestawCombo);
        if (zestawSprzetow == null) {
            JOptionPane.showMessageDialog(this, "Problem with DB to get ZestawSprzetow!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Sprzet sprzet = new Sprzet(nazwa, ilosc, zestawSprzetow);
        zestawSprzetow.addSprzetToList(sprzet);
        SprzetRepo sprzetRepo = new SprzetRepo();
        sprzetRepo.save(sprzet);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private ZestawSprzetow findInList(String selected) {
        String idText = selected.split(" ")[0];
        Long id = Long.valueOf(idText);
        for (int i = 0; i < zestawSprzetowList.size(); i++) {
            if (zestawSprzetowList.get(i).getId().equals(id)) {
                return zestawSprzetowList.get(i);
            }
        }
        return null;
    }

    private void fillZestawComboBox() {
        zestawComboBox.removeAllItems();
        zestawSprzetowList = new ZestawSprzetowRepo().getAll();
        if (zestawSprzetowList == null) {
            return;
        }
        if (zestawSprzetowList.isEmpty()) {
            return;
        }
        zestawSprzetowList.forEach(p -> zestawComboBox.addItem(p.getId() + " " + p.getDyscyplina()));
    }
}
