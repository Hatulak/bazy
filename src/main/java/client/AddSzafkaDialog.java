package client;

import Repository.SzafkaRepo;
import Repository.SzkolaRepo;
import model.Szafka;
import model.Szkola;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddSzafkaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField szafkaPojemnoscTextField;
    private JTextField szafkaHasloTextField;
    private JTextField szafkaNumerTextField;

    public AddSzafkaDialog() {
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
        if (szafkaNumerTextField.getText().isEmpty() || szafkaHasloTextField.getText().isEmpty() || szafkaPojemnoscTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of fields is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer szafkaNumer = Integer.parseInt(szafkaNumerTextField.getText());
        String szafkaHaslo = szafkaHasloTextField.getText();
        Integer szafkaPojemnosc = Integer.parseInt(szafkaPojemnoscTextField.getText());

        SzkolaRepo szkolaRepo = new SzkolaRepo();
        List<Szkola> szkolaList = szkolaRepo.getAll();
        Szkola szkola = szkolaList.get(0);

        SzafkaRepo szafkaRepo = new SzafkaRepo();
        szafkaRepo.save(new Szafka(szafkaNumer, szafkaHaslo, szafkaPojemnosc, null, szkola));

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
