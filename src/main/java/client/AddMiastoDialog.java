package client;

import Repository.MiastoRepo;
import model.Miasto;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddMiastoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nazwaTextField;
    private JTextField gminaTextField;
    private JTextField wojewodztwoTextField;
    private JTextField powiatTextField;

    public AddMiastoDialog() {
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

    public AddMiastoDialog(Miasto currentMiasto) {

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

        nazwaTextField.setText(currentMiasto.getNazwa());
        gminaTextField.setText(currentMiasto.getGmina());
        powiatTextField.setText(currentMiasto.getPowiat());
        wojewodztwoTextField.setText(currentMiasto.getWojewodztwo());
    }

    private void onEditOK() {
        //TODO Zrob tutaj tego merge'a
        String nazwa = nazwaTextField.getText();
        String gmina = gminaTextField.getText();
        String powiat = powiatTextField.getText();
        String wojewodztwo = wojewodztwoTextField.getText();
        if (nazwa.isEmpty() || wojewodztwo.isEmpty() || gmina.isEmpty() || powiat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        MiastoRepo miastoRepo = new MiastoRepo();
        List<Miasto> all = miastoRepo.getAll();
        Miasto miastoInList = MainClient.findMiastoInList(nazwa, all);
        if (miastoInList != null) {
            miastoInList.setNazwa(nazwa);
            miastoInList.setGmina(gmina);
            miastoInList.setPowiat(powiat);
            miastoInList.setWojewodztwo(wojewodztwo);
            miastoRepo.update(miastoInList);
        }
        dispose();
    }

    private void onOK() {
        // add your code here
        String nazwa = nazwaTextField.getText();
        String gmina = gminaTextField.getText();
        String powiat = powiatTextField.getText();
        String wojewodztwo = wojewodztwoTextField.getText();
        if (nazwa.isEmpty() || wojewodztwo.isEmpty() || gmina.isEmpty() || powiat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        MiastoRepo miastoRepo = new MiastoRepo();
        miastoRepo.save(new Miasto(nazwa, gmina, powiat, wojewodztwo));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
