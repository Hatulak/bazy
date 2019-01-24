package client;

import Repository.DzieckoRepo;
import Repository.GrupaRepo;
import Repository.RodzicRepo;
import Repository.SzafkaRepo;
import model.Dziecko;
import model.Grupa;
import model.Rodzic;
import model.Szafka;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddDzieckoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList rodziceList;
    private JComboBox grupaComboBox;
    private JTextField wiekTextField;
    private JTextField imieTextField;
    private JButton stworzRodziceButton;
    private JComboBox szafkaComboBox;
    private JButton usunRodzicButton;

    public AddDzieckoDialog() {
        usunRodzicButton.setVisible(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        rodziceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        rodziceList.addSelectionInterval(0, 2);

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
        updateGrupaComboBox();
        updateSzafkaComboBox();
        updateRodziceList();
    }

    public AddDzieckoDialog(String dziecko) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        rodziceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        rodziceList.addSelectionInterval(0, 2);
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
        stworzRodziceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRodzicDialog addRodzicDialog = new AddRodzicDialog();
                addRodzicDialog.pack();
                addRodzicDialog.setVisible(true);
                updateRodziceList();
            }
        });
        usunRodzicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        updateGrupaComboBox();
        updateSzafkaComboBox();
        updateRodziceList();
        DzieckoRepo dzieckoRepo = new DzieckoRepo();
        SzafkaRepo szafkaRepo = new SzafkaRepo();
        Dziecko currentDziecko = dzieckoRepo.getById(Long.parseLong(dziecko.split(" ")[0]));
        imieTextField.setText(currentDziecko.getImie());
        wiekTextField.setText(String.valueOf(currentDziecko.getWiek()));
        grupaComboBox.setSelectedItem(currentDziecko.getGrupa().getId() + " " + currentDziecko.getGrupa().getNazwa());
        Szafka dzieckoSzafka = szafkaRepo.getDzieckoSzafka(currentDziecko.getId());
        szafkaComboBox.setSelectedItem(dzieckoSzafka.getId() + " " + dzieckoSzafka.getNumer());
        List<Rodzic> rodzicList = new ArrayList<>(currentDziecko.getRodzicSet());
        rodzicList.forEach(rodzic -> rodziceList.setSelectedValue(rodzic.getId() + " " + rodzic.getImie() + " " + rodzic.getNazwisko(), true));
        //TODO Poprawić, aby dobrbze zaznaczało rodziców w liście
    }

    private void onEditOK() {
        //TODO Edycja dziecka
    }

    private void updateSzafkaComboBox() {
        szafkaComboBox.removeAllItems();
        SzafkaRepo szafkaRepo = new SzafkaRepo();
        List<Szafka> szafkaList = szafkaRepo.getEmptySzafkas();
        szafkaList.forEach(e -> szafkaComboBox.addItem(e.getId() + " " + e.getNumer()));
    }

    private void updateRodziceList() {
        RodzicRepo rodzicRepo = new RodzicRepo();
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (Rodzic rodzic :
                rodzicRepo.getAll()) {
            defaultListModel.addElement(String.format("%s %s %s", rodzic.getId().toString(), rodzic.getImie(), rodzic.getNazwisko()));
        }
        rodziceList.setModel(defaultListModel);
    }

    private void updateGrupaComboBox() {
        grupaComboBox.removeAllItems();
        GrupaRepo grupaRepo = new GrupaRepo();
        List<Grupa> grupaList = grupaRepo.getAll();
        grupaList.forEach(e -> grupaComboBox.addItem(e.getId() + " " + e.getNazwa()));
    }

    private void onOK() {
        String imie = imieTextField.getText();
        Integer wiek = null;
        try {
            wiek = Integer.parseInt(wiekTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Wiek musi być liczbą", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RodzicRepo rodzicRepo = new RodzicRepo();
        List selectedValuesList = rodziceList.getSelectedValuesList();
        Set<Rodzic> selectedRodziceSet = new HashSet<>();
        for (int i = 0; i < selectedValuesList.size(); i++) {
            String id = selectedValuesList.get(i).toString().split(" ")[0];
            selectedRodziceSet.add(rodzicRepo.getById(Long.parseLong(id)));
        }

        GrupaRepo grupaRepo = new GrupaRepo();
        Grupa grupa = grupaRepo.getById(Long.parseLong(grupaComboBox.getSelectedItem().toString().split(" ")[0]));

        Dziecko dziecko = new Dziecko(imie, wiek, grupa, selectedRodziceSet);
        DzieckoRepo dzieckoRepo = new DzieckoRepo();
        dzieckoRepo.save(dziecko);
        SzafkaRepo szafkaRepo = new SzafkaRepo();
        Szafka szafka = szafkaRepo.getById(Long.parseLong(szafkaComboBox.getSelectedItem().toString().split(" ")[0]));
        szafka.setDziecko(dziecko);
        szafkaRepo.update(szafka);


        if (imie.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedRodziceSet.size() < 1) {
            JOptionPane.showMessageDialog(this, "Dziecko powinno mieć chociaż jednego rodzica", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
