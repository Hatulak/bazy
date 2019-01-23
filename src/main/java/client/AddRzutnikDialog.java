package client;

import Repository.RzutnikRepo;
import lombok.extern.java.Log;
import model.Rzutnik;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

@Log
public class AddRzutnikDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField modelTextField;
    private JTextField jakoscObrazuTextField;
    private JXDatePicker dataZakupuDatePicker;
    private JXDatePicker dataWygasnieciaDatePicker;

    public AddRzutnikDialog() {
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
        String model = modelTextField.getText();
        String jakoscObrazu = jakoscObrazuTextField.getText();
        Date dataZakupu = dataZakupuDatePicker.getDate();
        Date dataWygasniecia = dataWygasnieciaDatePicker.getDate();

        if (model.isEmpty() || jakoscObrazu.isEmpty() || dataWygasniecia == null || dataZakupu == null) {
            log.info("Empty field");
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RzutnikRepo rzutnikRepo = new RzutnikRepo();
        rzutnikRepo.save(new Rzutnik(model, jakoscObrazu, dataZakupu, dataWygasniecia));

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
