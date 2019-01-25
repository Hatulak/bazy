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

    private Szafka szafka;

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

    public AddSzafkaDialog(Szafka szafka) {
        this.szafka = szafka;
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

        szafkaNumerTextField.setText(szafka.getNumer().toString());
        szafkaHasloTextField.setText(szafka.getHaslo());
        szafkaPojemnoscTextField.setText(szafka.getPojemnosc().toString());

    }

    private void onOK() {
        if (szafkaNumerTextField.getText().isEmpty() || szafkaHasloTextField.getText().isEmpty() || szafkaPojemnoscTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of fields is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer szafkaNumer;
        String szafkaHaslo = new String();
        szafkaHaslo = szafkaHasloTextField.getText();
        Integer szafkaPojemnosc;
        try {
            szafkaNumer = Integer.parseInt(szafkaNumerTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Numer szafki musi być liczbą", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            szafkaPojemnosc = Integer.parseInt(szafkaPojemnoscTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Pojemność szafki musi być liczbą", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }



        if (szafka == null) {
            SzkolaRepo szkolaRepo = new SzkolaRepo();
            List<Szkola> szkolaList = szkolaRepo.getAll();
            Szkola szkola = szkolaList.get(0);

            SzafkaRepo szafkaRepo = new SzafkaRepo();
            szafkaRepo.save(new Szafka(szafkaNumer, szafkaHaslo, szafkaPojemnosc, null, szkola));
        } else {
            szafka.setNumer(szafkaNumer);
            szafka.setHaslo(szafkaHaslo);
            szafka.setPojemnosc(szafkaPojemnosc);
            SzafkaRepo szafkaRepo = new SzafkaRepo();
            szafkaRepo.update(szafka);
        }

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
