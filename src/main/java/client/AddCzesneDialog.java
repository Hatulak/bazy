package client;

import Repository.CzesneRepo;
import model.Czesne;
import model.Dziecko;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class AddCzesneDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField czesneAmountTextField;
    private JXDatePicker czesneDatePicker;

    private Dziecko dziecko;

    private Czesne czesne;

    public AddCzesneDialog(Dziecko dziecko) {
        this.dziecko = dziecko;
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

    public AddCzesneDialog(Dziecko dziecko, Czesne czesne) {
        this.dziecko = dziecko;
        this.czesne = czesne;

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

        czesneDatePicker.setDate(czesne.getDataOplaty());
        czesneAmountTextField.setText(czesne.getKwota().toString());
    }

    private void onOK() {
        String czesneAmount = czesneAmountTextField.getText();
        Date czesneDate = czesneDatePicker.getDate();

        if (czesneAmount.isEmpty() || czesneDate == null) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dziecko == null) {
            JOptionPane.showMessageDialog(this, "Nie można dodać czesnych bez wybrania dziecka", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double czesneAmountDouble = Double.parseDouble(czesneAmount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Kwota czesnych musi być liczbą!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (czesne == null) {
            CzesneRepo czesneRepo = new CzesneRepo();
            czesneRepo.save(new Czesne(czesneDate, Double.parseDouble(czesneAmount), dziecko));
            dispose();
            return;
        }

        czesne.setDataOplaty(czesneDate);
        czesne.setKwota(Double.parseDouble(czesneAmount));

        CzesneRepo czesneRepo = new CzesneRepo();
        czesneRepo.update(czesne);

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
