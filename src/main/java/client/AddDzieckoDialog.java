package client;

import Repository.RodzicRepo;
import model.Rodzic;

import javax.swing.*;
import java.awt.event.*;

public class AddDzieckoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList rodziceList;
    private JComboBox textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton stworzRodziceButton;

    public AddDzieckoDialog() {
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
        stworzRodziceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRodzicDialog addRodzicDialog = new AddRodzicDialog();
                addRodzicDialog.pack();
                addRodzicDialog.setVisible(true);
                updateRodziceList();
            }
        });
        updateRodziceList();
    }

    private void updateRodziceList() {
        RodzicRepo rodzicRepo = new RodzicRepo();
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (Rodzic rodzic :
                rodzicRepo.getAll()) {
            defaultListModel.addElement(String.format("%s %s", rodzic.getImie(), rodzic.getNazwisko()));
        }
        rodziceList.setModel(defaultListModel);
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
