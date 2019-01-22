package client;

import Repository.MiastoRepo;
import lombok.extern.java.Log;
import model.Miasto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

@Log
public class MainClient extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField dzieckoWiekTextField;
    private JTextField dzieckoNazwiskoTextField;
    private JTextField dzieckoImieTextField;
    private JList dzieckoRodziceList;
    private JComboBox dzieckoGrupaComboBox;
    private JComboBox dzieckoUczenComboBox;
    private JButton dodajDzieckoButton;
    private JButton usunDzieckoButton;
    private JButton edytujDzieckoButton;
    private JTextField grupaNauczycielTextField;
    private JList grupaUczniowieList;
    private JComboBox grupaGrupaComboBox;
    private JTextField telefonTextField;
    private JTextField stopienTextField;
    private JTextField miastoTextField;
    private JTextField adresTextField;
    private JTextField szkolaTextField;
    private JList szafkiSzkolaList;
    private JList nauczycieleSzkolaList;
    private JTextField grupaWiekTextField;
    private JTextField grupaSalaTextField;
    private JComboBox nauczycielComboBox;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JTextField emailTextField;
    private JComboBox salaComboBox;
    private JTextField salaSzkolaTextField;
    private JTextField liczbaKrzeselTextField;
    private JTextField liczbaLawekTextField;
    private JTextField rzutnikTextField;
    private JList listaKomputerowList;
    private JTextField nazwaSzkolaTextField;
    private JTextField adresSzkolaTextField;
    private JTextField miastoSzkolaTextField;
    private JTextField patronSzkolaTextField;
    private JList saleSzkolaList;
    private JButton dodajGrupaButton;
    private JButton usunGrupaButton;
    private JButton edytujGrupaButton;
    private JButton dodajNauczycielButton;
    private JButton edytujNauczycielButton;
    private JButton usunNauczycielButton;
    private JButton dodajSalaButton;
    private JButton edytujSalaButton;
    private JButton usunSalaButton;
    private JButton dodajSzkolaButton;
    private JButton edytujSzkolaButton;
    private JButton usunSzkolaButton;
    private JButton dodajHaleButton;
    private JButton edytujHaleButton;
    private JButton usunHaleButton;
    private JTextField wielkoscTextField;
    private JComboBox halaSportowaSzkolaComboBox;
    private JCheckBox trybunaCheckBox;
    private JList zestawySprzetowList;
    private JTextField numerSaliTextField;
    private JComboBox miastoComboBox;
    private JTextField miastoNazwaTextField;
    private JTextField miastoGminaTextField;
    private JTextField miastoPowiatTextField;
    private JTextField miastoWojewodztwoTextField;
    private JButton dodajMiastoButton;
    private JButton edytujMiastoButton;
    private JButton usunMiastoButton;
    private JButton dodajCzesneButton;
    private JButton usunCzesneButton;
    private JButton edytujCzesneButton;
    private JList czesneCzesneList;
    private JComboBox comboBox1;
    private JButton dodajSzafkeButton;
    private JButton usunSzafkeButton;
    private JButton edytujSzafkeButton;
    private JComboBox szafkaComboBox;
    private JTextField szafkaNumerTextField;
    private JTextField szafkaHasloTextField;
    private JTextField szafkaPojemnoscTextField;
    private JComboBox szafkaDzieckoComboBox;
    private List<Miasto> miastoList;

    public MainClient() {
        add(panel1);
        setTitle("School app");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 400));
        pack();
        fillComboboxMiasto();
        miastoComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedMiasto = miastoComboBox.getSelectedItem().toString();
                if (selectedMiasto.isEmpty()) {
                    log.info("Get empty string from combobox");
                    return;
                }
                Miasto miastoFromList = null;
                miastoFromList = findMiastoInList(selectedMiasto, miastoList);
                if (miastoFromList == null) {
                    log.info("city is null");
                    return;
                }
                miastoNazwaTextField.setText(miastoFromList.getNazwa());
                miastoPowiatTextField.setText(miastoFromList.getPowiat());
                miastoGminaTextField.setText(miastoFromList.getGmina());
                miastoWojewodztwoTextField.setText(miastoFromList.getWojewodztwo());
            }
        });
        dodajDzieckoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDzieckoDialog addDzieckoDialog = new AddDzieckoDialog();
                addDzieckoDialog.pack();
                addDzieckoDialog.setVisible(true);
            }
        });
        dodajGrupaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGrupaDialog addGrupaDialog = new AddGrupaDialog();
                addGrupaDialog.pack();
                addGrupaDialog.setVisible(true);
            }
        });
        dodajNauczycielButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNauczycielDialog addNauczycielDialog = new AddNauczycielDialog();
                addNauczycielDialog.pack();
                addNauczycielDialog.setVisible(true);
            }
        });
        dodajSalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSalaDialog addSalaDialog = new AddSalaDialog();
                addSalaDialog.pack();
                addSalaDialog.setVisible(true);
            }
        });
        dodajHaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddHalaSportowaDialog addHalaSportowaDialog = new AddHalaSportowaDialog();
                addHalaSportowaDialog.pack();
                addHalaSportowaDialog.setVisible(true);
            }
        });
        dodajSzkolaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSzkolaDialog addSzkolaDialog = new AddSzkolaDialog();
                addSzkolaDialog.pack();
                addSzkolaDialog.setVisible(true);
            }
        });
        dodajMiastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMiastoDialog addMiastoDialog = new AddMiastoDialog();
                addMiastoDialog.pack();
                addMiastoDialog.setVisible(true);
                fillComboboxMiasto();
            }
        });
        dodajCzesneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCzesneDialog addCzesneDialog = new AddCzesneDialog();
                addCzesneDialog.pack();
                addCzesneDialog.setVisible(true);
            }
        });
        dodajSzafkeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSzafkaDialog addSzafkaDialog = new AddSzafkaDialog();
                addSzafkaDialog.pack();
                addSzafkaDialog.setVisible(true);
            }
        });

    }


    static Miasto findMiastoInList(String selectedMiasto, List<Miasto> miastoList) {
        for (int i = 0; i < miastoList.size(); i++) {
            if (miastoList.get(i).getNazwa().equals(selectedMiasto)) {
                return miastoList.get(i);
            }
        }
        return null;
    }

    private void fillComboboxMiasto() {
        miastoComboBox.removeAllItems();
        MiastoRepo miastoRepo = new MiastoRepo();
        miastoList = miastoRepo.getAll();
        if (miastoList == null) {
            log.info("Empty list with miasto's get from DB");
        } else {
            miastoList.forEach(p -> miastoComboBox.addItem(p.getNazwa()));
        }
        String miastoComboString = miastoComboBox.getSelectedItem().toString();
        if (miastoComboString.isEmpty()) {
            return;
        }
        Miasto miastoInList = findMiastoInList(miastoComboString, miastoList);
        miastoNazwaTextField.setText(miastoInList.getNazwa());
        miastoPowiatTextField.setText(miastoInList.getPowiat());
        miastoGminaTextField.setText(miastoInList.getGmina());
        miastoWojewodztwoTextField.setText(miastoInList.getWojewodztwo());
    }
}
