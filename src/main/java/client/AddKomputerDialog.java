package client;

import Repository.KomputerRepo;
import lombok.extern.java.Log;
import model.Komputer;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

@Log
public class AddKomputerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextPane specyfikacjaTextPane;
    private JXDatePicker dataZakupuDatePicker;
    private JXDatePicker dataWygasnieciaDatePicker;

    public AddKomputerDialog() {
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

    private void onOK() {
        Date dataZakupu = dataZakupuDatePicker.getDate();
        Date dataWygasniecia = dataWygasnieciaDatePicker.getDate();
        String specyfikacja = specyfikacjaTextPane.getText();

        if (specyfikacja.isEmpty() || dataZakupu == null || dataWygasniecia == null) {
            log.info("Empty field");
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        KomputerRepo komputerRepo = new KomputerRepo();
        komputerRepo.save(new Komputer(dataZakupu, dataWygasniecia, specyfikacja));

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
