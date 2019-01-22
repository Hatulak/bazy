package client;

import Repository.MiastoRepo;
import lombok.extern.java.Log;
import model.Miasto;

import javax.swing.*;
import java.awt.event.*;
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
    private JList saleList;
    private JList szafkiList;
    private JList nauczycieleList;
    private JButton stworzMiastoButton;

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
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
