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
    private Dziecko dzieckoCon;
    private DefaultListModel<String> defaultListModel;

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

    public AddDzieckoDialog(String dzieckoString) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        DzieckoRepo dzieckoRepo = new DzieckoRepo();
        dzieckoCon = dzieckoRepo.getById(Long.valueOf(dzieckoString.split(" ")[0]));
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
                if (rodziceList.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "No item is selected on list!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<String> selectedValuesList = rodziceList.getSelectedValuesList();
                RodzicRepo rodzicRepo = new RodzicRepo();
                if (defaultListModel.size() <= selectedValuesList.size()) {
                    JOptionPane.showMessageDialog(null, "Dziecko should have at least one rodzic, cannot delete all rodzice!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (String selectedRodzic : selectedValuesList) {
                    Long id = Long.valueOf(selectedRodzic.split(" ")[0]);
                    Rodzic rodzicById = rodzicRepo.getById(id);
                    rodzicById.removeDziecko(dzieckoCon);
                    rodzicRepo.update(rodzicById);
                    rodzicRepo.remove(rodzicById);
                    dzieckoCon.removeRodzic(rodzicById);
                    dzieckoRepo.update(dzieckoCon);
                }
                updateRodziceList();
            }
        });
        updateGrupaComboBox();
        updateSzafkaComboBox();
        updateRodziceList();
        SzafkaRepo szafkaRepo = new SzafkaRepo();
        imieTextField.setText(dzieckoCon.getImie());
        wiekTextField.setText(String.valueOf(dzieckoCon.getWiek()));
        grupaComboBox.setSelectedItem(dzieckoCon.getGrupa().getId() + " " + dzieckoCon.getGrupa().getNazwa());
        Szafka dzieckoSzafka = szafkaRepo.getDzieckoSzafka(dzieckoCon.getId());
        szafkaComboBox.setSelectedItem(dzieckoSzafka.getId() + " " + dzieckoSzafka.getNumer());
        List<Rodzic> rodzicList = new ArrayList<>(dzieckoCon.getRodzicSet());
        rodzicList.forEach(rodzic -> {
            rodziceList.setSelectedValue(rodzic.getId() + " " + rodzic.getImie() + " " + rodzic.getNazwisko(), true);
            if(rodzicList.size()>1) {
                rodziceList.getSelectionModel().setSelectionInterval(0, 1);
            }
        });
    }

    private void onEditOK() {
        //TODO Edycja dziecka
        String imie = imieTextField.getText();
        Integer wiek = null;
        try {
            wiek = Integer.parseInt(wiekTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Wiek musi być liczbą", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (rodziceList.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Dziecko should have at least one rodzic ", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (imie.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
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
        if (grupaComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Empty combobox or DB problem!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Grupa grupa = grupaRepo.getById(Long.parseLong(grupaComboBox.getSelectedItem().toString().split(" ")[0]));
        SzafkaRepo szafkaRepo = new SzafkaRepo();
        if (szafkaComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Empty combobox or DB problem!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Szafka szafka = szafkaRepo.getById(Long.parseLong(szafkaComboBox.getSelectedItem().toString().split(" ")[0]));
        Szafka szafkaDB = findSzafkaOfDziecko(dzieckoCon);
        if (szafkaDB != null) {
            szafkaDB.setDziecko(null);
            szafkaRepo.update(szafkaDB);
        }
        Grupa grupafDziecko = findGrupafDziecko(dzieckoCon);
        if (grupafDziecko != null) {
            grupafDziecko.removeDziecko(dzieckoCon);
            new GrupaRepo().update(grupafDziecko);
        }
        Dziecko dziecko = new Dziecko(imie, wiek, grupa, selectedRodziceSet);
        dziecko.setId(dzieckoCon.getId());
        DzieckoRepo dzieckoRepo = new DzieckoRepo();
        dzieckoRepo.update(dziecko);
        szafka.setDziecko(dziecko);
        szafkaRepo.update(szafka);
        dispose();
    }

    private Szafka findSzafkaOfDziecko(Dziecko dzieckoDB) {
        List<Szafka> szafkaListDB = new SzafkaRepo().getAll();
        if (szafkaListDB == null) {
            return null;
        }
        if (szafkaListDB.isEmpty()) {
            return null;
        }
        for (int i = 0; i < szafkaListDB.size(); i++) {
            if (szafkaListDB.get(i).getDziecko() == null) {
                continue;
            }
            if (szafkaListDB.get(i).getDziecko().getId().equals(dzieckoDB.getId())) {
                return szafkaListDB.get(i);
            }
        }
        return null;
    }

    private Grupa findGrupafDziecko(Dziecko dzieckoDB) {
        List<Grupa> grupaListDB = new GrupaRepo().getAll();
        if (grupaListDB == null) {
            return null;
        }
        if (grupaListDB.isEmpty()) {
            return null;
        }
        for (Grupa grupa : grupaListDB) {
            if (grupa.getDzieckoList() == null) {
                continue;
            }
            for (Dziecko dziecko : grupa.getDzieckoList()) {
                if (dziecko.getId().equals(dzieckoDB.getId())) {
                    return grupa;
                }

            }
        }
        return null;
    }

    private void updateSzafkaComboBox() {
        szafkaComboBox.removeAllItems();
        SzafkaRepo szafkaRepo = new SzafkaRepo();
        List<Szafka> szafkaList = szafkaRepo.getEmptySzafkas();
        szafkaList.forEach(e -> szafkaComboBox.addItem(e.getId() + " " + e.getNumer()));
    }

    private void updateRodziceList() {
        RodzicRepo rodzicRepo = new RodzicRepo();
        defaultListModel = new DefaultListModel<>();
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

        if (imie.isEmpty()) {
            JOptionPane.showMessageDialog(this, "One of field is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        RodzicRepo rodzicRepo = new RodzicRepo();
        List selectedValuesList = rodziceList.getSelectedValuesList();
        Set<Rodzic> selectedRodziceSet = new HashSet<>();
        for (int i = 0; i < selectedValuesList.size(); i++) {
            String id = selectedValuesList.get(i).toString().split(" ")[0];
            selectedRodziceSet.add(rodzicRepo.getById(Long.parseLong(id)));
        }

        if (selectedRodziceSet.size() < 1) {
            JOptionPane.showMessageDialog(this, "Dziecko powinno mieć chociaż jednego rodzica", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        GrupaRepo grupaRepo = new GrupaRepo();
        if(grupaComboBox.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Nie wybrano grupy!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Grupa grupa = grupaRepo.getById(Long.parseLong(grupaComboBox.getSelectedItem().toString().split(" ")[0]));

        SzafkaRepo szafkaRepo = new SzafkaRepo();
        if (szafkaComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Nie wybrano szafki!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Szafka szafka = szafkaRepo.getById(Long.parseLong(szafkaComboBox.getSelectedItem().toString().split(" ")[0]));
        Dziecko dziecko = new Dziecko(imie, wiek, grupa, selectedRodziceSet);
        DzieckoRepo dzieckoRepo = new DzieckoRepo();
        dzieckoRepo.save(dziecko);
        szafka.setDziecko(dziecko);
        szafkaRepo.update(szafka);


        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
