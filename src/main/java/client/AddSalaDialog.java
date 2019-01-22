package client;

import Repository.KomputerRepo;
import Repository.RzutnikRepo;
import lombok.extern.java.Log;
import model.Komputer;
import model.Rzutnik;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

@Log
public class AddSalaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField numerTextField;
    private JTextField liczbaKrzeselTextField;
    private JTextField liczbaLawekTextField;
    private JComboBox szkolaComboBox;
    private JComboBox rzutnikComboBox;
    private JList komputeryJList;
    private JButton stworzRzutnikButton;
    private JButton stworzKomputerButton;
    private List<Rzutnik> rzutnikList;
    private List<Komputer> komputerList;

    public AddSalaDialog() {
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
        stworzRzutnikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRzutnikDialog addRzutnikDialog = new AddRzutnikDialog();
                addRzutnikDialog.pack();
                addRzutnikDialog.setVisible(true);
                fillComboboxRzutnik();
            }
        });
        stworzKomputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddKomputerDialog addKomputerDialog = new AddKomputerDialog();
                addKomputerDialog.pack();
                addKomputerDialog.setVisible(true);
                fillListViewKomupter();

            }
        });
        fillComboboxRzutnik();
        fillListViewKomupter();
    }

    private void fillListViewKomupter() {
        komputeryJList.removeAll();
        KomputerRepo komputerRepo = new KomputerRepo();
        komputerList = komputerRepo.getAllKomputersWhereSalaIdIsNull();
        if (komputerList == null) {
            log.info("Empty list with miasto's get from DB");
        } else {
            DefaultListModel komputerListModel = new DefaultListModel();
            komputerList.forEach(e -> komputerListModel.addElement(e.getId().toString()));
            komputeryJList.setModel(komputerListModel);
        }
    }

    private void fillComboboxRzutnik() {
        rzutnikComboBox.removeAllItems();
        RzutnikRepo rzutnikRepo = new RzutnikRepo();
        rzutnikList = rzutnikRepo.getRzutniksWhereRzutnikInSalaIsNull();
        if (rzutnikList == null) {
            log.info("Empty list with miasto's get from DB");
        } else {
            rzutnikList.forEach(p -> rzutnikComboBox.addItem(p.getModel()));
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
