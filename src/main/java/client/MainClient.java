package client;

import Repository.*;
import lombok.extern.java.Log;
import model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JComboBox czesneDzieckoComboBox;
    private JButton dodajSzafkeButton;
    private JButton usunSzafkeButton;
    private JButton edytujSzafkeButton;
    private JComboBox szafkaComboBox;
    private JTextField szafkaNumerTextField;
    private JTextField szafkaHasloTextField;
    private JTextField szafkaPojemnoscTextField;
    private JTextField szafkaDzieckoTextField;
    private JButton dodajSprzetButton;
    private JButton dodajZestawSprzetowButton;
    private List<Miasto> miastoList;
    private List<Nauczyciel> nauczycielList;
    private List<Grupa> grupaList;
    private NauczycielRepo nauczycielRepo;

    public MainClient() {
        nauczycielRepo = new NauczycielRepo();
        // todo dorobic edycje grupy i usuwanie :3
        add(panel1);
        setTitle("School app");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 400));
        pack();
        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshEverything();
            }
        });
        fillComboboxMiasto();
        fillComboboxNauczyciel();
        fillGrupaGrupaComboBox();
        NauczycielComboBoxListener nauczycielComboBoxListener = new NauczycielComboBoxListener();
        nauczycielComboBox.addActionListener(nauczycielComboBoxListener);
        MiastoComboBoxActionListener miastoComboBoxActionListener = new MiastoComboBoxActionListener();
        miastoComboBox.addItemListener(miastoComboBoxActionListener);
        GrupaComboBoxListener grupaComboBoxListener = new GrupaComboBoxListener();
        grupaGrupaComboBox.addActionListener(grupaComboBoxListener);
        SzkolaRepo szkolaRepo = new SzkolaRepo();
        if (szkolaRepo.getAll().size() == 1) {
            dodajSzkolaButton.setEnabled(false);
            edytujSzkolaButton.setEnabled(true);
            usunSzkolaButton.setEnabled(true);
        }
        dodajDzieckoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDzieckoDialog addDzieckoDialog = new AddDzieckoDialog();
                addDzieckoDialog.pack();
                addDzieckoDialog.setVisible(true);
                refreshEverything();
            }
        });
        dodajGrupaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGrupaDialog addGrupaDialog = new AddGrupaDialog();
                addGrupaDialog.pack();
                addGrupaDialog.setVisible(true);
                grupaGrupaComboBox.removeActionListener(grupaComboBoxListener);
                fillGrupaGrupaComboBox();
                grupaGrupaComboBox.addActionListener(grupaComboBoxListener);
                refreshEverything();
            }
        });
        dodajNauczycielButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNauczycielDialog addNauczycielDialog = new AddNauczycielDialog();
                addNauczycielDialog.pack();
                addNauczycielDialog.setVisible(true);
                nauczycielComboBox.removeActionListener(nauczycielComboBoxListener);
                fillComboboxNauczyciel();
                nauczycielComboBox.addActionListener(nauczycielComboBoxListener);
                refreshEverything();
            }
        });
        edytujNauczycielButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nauczycielComboBox.removeActionListener(nauczycielComboBoxListener);
                if (nauczycielComboBox.getSelectedItem().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nauczyciel is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Nauczyciel nauczycielInList = findNauczycielInList(nauczycielComboBox.getSelectedItem().toString(), nauczycielList);
                AddNauczycielDialog addNauczycielDialog = new AddNauczycielDialog(nauczycielInList);
                addNauczycielDialog.pack();
                addNauczycielDialog.setVisible(true);
                fillComboboxNauczyciel();
                nauczycielComboBox.addActionListener(nauczycielComboBoxListener);
                refreshEverything();
            }
        });
        dodajSalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSalaDialog addSalaDialog = new AddSalaDialog();
                addSalaDialog.pack();
                addSalaDialog.setVisible(true);
                refreshEverything();
            }
        });
        dodajHaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo trzeba przerobbic tak by przycisk dodawnai zestawu sprzetu był nad przyciskiem dodaj sprzet na ekranie głónym
                // do tego usunąc dodawanie zestawu sprzetu podczas tworzenia hali sportowej, i blokowac dodawanie jak juz sie dodało sale bo ona jest jedna tak jak szkola :3

                AddHalaSportowaDialog addHalaSportowaDialog = new AddHalaSportowaDialog();
                addHalaSportowaDialog.pack();
                addHalaSportowaDialog.setVisible(true);
                refreshEverything();
            }
        });
        dodajSzkolaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSzkolaDialog addSzkolaDialog = new AddSzkolaDialog();
                addSzkolaDialog.pack();
                addSzkolaDialog.setVisible(true);
                dodajSzkolaButton.setEnabled(false);
                edytujSzkolaButton.setEnabled(true);
                usunSzkolaButton.setEnabled(true);
                fillComboboxNauczyciel();
                refreshEverything();
            }
        });
        dodajMiastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMiastoDialog addMiastoDialog = new AddMiastoDialog();
                addMiastoDialog.pack();
                addMiastoDialog.setVisible(true);
                miastoComboBox.removeItemListener(miastoComboBoxActionListener);
                fillComboboxMiasto();
                miastoComboBox.addItemListener(miastoComboBoxActionListener);
                refreshEverything();
            }
        });
        dodajCzesneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DzieckoRepo dzieckoRepo = new DzieckoRepo();
                String id_imie = czesneDzieckoComboBox.getSelectedItem().toString(); //TODO - hatulak
                String id = new String();
                int i = 0;
                while (id_imie.charAt(i) != ' ' && i < id_imie.length()) {
                    id += id_imie.charAt(i);
                    i++;
                }
                Dziecko dziecko = dzieckoRepo.getById(Long.parseLong(id));
                AddCzesneDialog addCzesneDialog = new AddCzesneDialog(dziecko);
                addCzesneDialog.pack();
                addCzesneDialog.setVisible(true);
                refreshEverything();
            }
        });
        dodajSzafkeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSzafkaDialog addSzafkaDialog = new AddSzafkaDialog();
                addSzafkaDialog.pack();
                addSzafkaDialog.setVisible(true);
                refreshEverything();
            }
        });

        edytujMiastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Miasto currentMiasto = findMiastoInList(miastoComboBox.getSelectedItem().toString(), miastoList);
                if (currentMiasto == null) {
                    log.info("found null in edytujmiastobuttonaction");
                    return;
                }
                AddMiastoDialog editMiastoDialog = new AddMiastoDialog(currentMiasto);
                editMiastoDialog.pack();
                editMiastoDialog.setVisible(true);
                miastoComboBox.removeItemListener(miastoComboBoxActionListener);
                fillComboboxMiasto();
                miastoComboBox.addItemListener(miastoComboBoxActionListener);
                refreshEverything();
            }
        });
        usunMiastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miastoComboBox.removeItemListener(miastoComboBoxActionListener);
                if (miastoComboBox.getSelectedItem().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "City is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Miasto miastoInList = findMiastoInList(miastoComboBox.getSelectedItem().toString(), miastoList);
                if (miastoInList == null) {
                    log.info("found null in deletemiastobuttonaction");
                    return;
                }
                MiastoRepo miastoRepo = new MiastoRepo();
                miastoRepo.remove(miastoInList);
                fillComboboxMiasto();
                miastoComboBox.addItemListener(miastoComboBoxActionListener);
                refreshEverything();
            }
        });
        usunNauczycielButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nauczycielComboBox.removeActionListener(nauczycielComboBoxListener);
                if (nauczycielComboBox.getSelectedItem().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nauczyciel is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String[] split = nauczycielComboBox.getSelectedItem().toString().split(" ");
                Long idValue = Long.valueOf(split[0]);
                Nauczyciel byId = nauczycielRepo.getById(idValue);
                if (byId == null) {
                    return;
                }
                //todo trzeba to ogarnac czemu nie działa do konca :c
                nauczycielRepo.remove(byId);
                fillComboboxNauczyciel();
                nauczycielComboBox.addActionListener(nauczycielComboBoxListener);
                refreshEverything();
            }
        });
        usunSzkolaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajSzkolaButton.setEnabled(true);
                edytujSzkolaButton.setEnabled(false);
                usunSzkolaButton.setEnabled(false);
                refreshEverything();
            }
        });
        edytujSzkolaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSzkolaDialog editSzkolaDialog = new AddSzkolaDialog(szkolaRepo.getAll().get(0));
                editSzkolaDialog.pack();
                editSzkolaDialog.setVisible(true);
                refreshEverything();
            }
        });
        edytujNauczycielButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NauczycielRepo nauczycielRepo = new NauczycielRepo();
                Nauczyciel nauczyciel = getCurrentNauczyciel(nauczycielComboBox.getSelectedItem().toString(), nauczycielRepo.getAll());
                AddNauczycielDialog editNauczycielDialog = new AddNauczycielDialog(nauczyciel);
                editNauczycielDialog.pack();
                editNauczycielDialog.setVisible(true);
                refreshEverything();
            }
        });
        salaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (salaComboBox.getSelectedItem() == null) {
                    numerSaliTextField.setText("");
                    liczbaKrzeselTextField.setText("");
                    liczbaLawekTextField.setText("");
                    rzutnikTextField.setText("");
                    salaSzkolaTextField.setText("");
                    listaKomputerowList.setListData(new String[]{""});
                    return;
                }
                SalaRepo salaRepo = new SalaRepo();
                List<Sala> salaList = salaRepo.getBySalaNumber(salaComboBox.getSelectedItem().toString());
                numerSaliTextField.setText(salaList.get(0).getNumerSali());
                liczbaKrzeselTextField.setText(salaList.get(0).getLiczbaKrzesel().toString());
                liczbaLawekTextField.setText(salaList.get(0).getLiczbaLawek().toString());
                if (salaList.get(0).getRzutnik() == null) {
                    rzutnikTextField.setText("BRAK");
                } else {
                    rzutnikTextField.setText(salaList.get(0).getRzutnik().getModel());
                }
                if (salaList.get(0).getSzkola() == null) {
                    salaSzkolaTextField.setText("BRAK");
                } else {
                    salaSzkolaTextField.setText(salaList.get(0).getSzkola().getNazwa());
                }

                KomputerRepo komputerRepo = new KomputerRepo();
                List<Komputer> komputerList = komputerRepo.getBySala(salaList.get(0));

                DefaultListModel komputerListModel = new DefaultListModel();
                komputerList.forEach(s -> komputerListModel.addElement(s.getId().toString()));
                listaKomputerowList.setModel(komputerListModel);

                edytujSalaButton.setEnabled(true);
                usunSalaButton.setEnabled(true);
            }
        });
        usunSalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalaRepo salaRepo = new SalaRepo();
                List<Sala> salaList = salaRepo.getBySalaNumber(salaComboBox.getSelectedItem().toString());
                salaList.get(0).setRzutnik(null);
                salaList.get(0).setKomputerList(null);
                salaList.get(0).setSzkola(null);
                KomputerRepo komputerRepo = new KomputerRepo();
                List<Komputer> komputerList = komputerRepo.getBySala(salaList.get(0));
                komputerList.forEach(c -> {
                    c.setSala(null);
                    komputerRepo.update(c);
                });
                salaRepo.remove(salaList.get(0));
                refreshEverything();
            }
        });
        dodajSprzetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSprzetDialog addSprzetDialog = new AddSprzetDialog();
                addSprzetDialog.pack();
                addSprzetDialog.setVisible(true);
            }
        });
        dodajZestawSprzetowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddZestawDialog addZestawDialog = new AddZestawDialog();
                addZestawDialog.pack();
                addZestawDialog.setVisible(true);
            }
        });
    }

    private Nauczyciel getCurrentNauczyciel(String nauczyciel, List<Nauczyciel> all) {
        for (int i = 0; i < all.size(); i++) {
            if (String.format("%s %s %s", all.get(i).getId(), all.get(i).getImie(), all.get(i).getNazwisko()).equals(nauczyciel)) {
                return all.get(i);
            }
        }
        return null;
    }

    private void refreshEverything() {
        DzieckoRepo dzieckoRepo = new DzieckoRepo();
        GrupaRepo grupaRepo = new GrupaRepo();
        MiastoRepo miastoRepo = new MiastoRepo();
        NauczycielRepo nauczycielRepo = new NauczycielRepo();
        SalaRepo salaRepo = new SalaRepo();
        SzafkaRepo szafkaRepo = new SzafkaRepo();
        List<Dziecko> dzieci = dzieckoRepo.getAll();
        List<Grupa> grupy = grupaRepo.getAll();
        List<Miasto> miasta = miastoRepo.getAll();
        List<Nauczyciel> nauczyciele = nauczycielRepo.getAll();
        List<Sala> sale = salaRepo.getAll();
        List<Szafka> szafki = szafkaRepo.getAll();
        NauczycielComboBoxListener nauczycielComboBoxListener = new NauczycielComboBoxListener();
        MiastoComboBoxActionListener miastoComboBoxActionListener = new MiastoComboBoxActionListener();
        GrupaComboBoxListener grupaComboBoxListener = new GrupaComboBoxListener();
        miastoComboBox.removeItemListener(miastoComboBoxActionListener);
        grupaGrupaComboBox.removeActionListener(grupaComboBoxListener);
        nauczycielComboBox.removeActionListener(nauczycielComboBoxListener);
        miastoComboBox.removeAllItems();
        dzieckoGrupaComboBox.removeAllItems();
        grupaGrupaComboBox.removeAllItems();
        czesneDzieckoComboBox.removeAllItems();
        nauczycielComboBox.removeAllItems();
        salaComboBox.removeAllItems();
        szafkaComboBox.removeAllItems();
        miasta.forEach(i -> miastoComboBox.addItem(i.getNazwa()));
        dzieci.forEach(i -> czesneDzieckoComboBox.addItem(i.getId() + " " + i.getImie()));
        grupy.forEach(i -> {
            dzieckoGrupaComboBox.addItem(i.getNazwa());
            grupaGrupaComboBox.addItem(i.getNazwa());
        });
        nauczyciele.forEach(i -> nauczycielComboBox.addItem(String.format("%s %s %s", i.getId(), i.getImie(), i.getNazwisko())));
        sale.forEach(i -> salaComboBox.addItem(i.getNumerSali()));
        szafki.forEach(i -> szafkaComboBox.addItem(i.getNumer()));
        grupaGrupaComboBox.addActionListener(grupaComboBoxListener);
        nauczycielComboBox.addActionListener(nauczycielComboBoxListener);
        miastoComboBox.addItemListener(miastoComboBoxActionListener);

    }

    private class MiastoComboBoxActionListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (miastoComboBox.getSelectedItem() == null) {
                return;
            }
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
        if (miastoComboBox.getSelectedItem() == null) {
            return;
        }
        String miastoComboString = miastoComboBox.getSelectedItem().toString();
        if (miastoComboString.isEmpty()) {
            return;
        }
        Miasto miastoInList = findMiastoInList(miastoComboString, miastoList);
        edytujMiastoButton.setEnabled(true);
        usunMiastoButton.setEnabled(true);
        miastoNazwaTextField.setText(miastoInList.getNazwa());
        miastoPowiatTextField.setText(miastoInList.getPowiat());
        miastoGminaTextField.setText(miastoInList.getGmina());
        miastoWojewodztwoTextField.setText(miastoInList.getWojewodztwo());
    }

    private void fillComboboxNauczyciel() {
        nauczycielComboBox.removeAllItems();
        nauczycielList = nauczycielRepo.getAll();
        if (nauczycielList == null) {
            log.info("Empty list with miasto's get from DB");
        } else {
            nauczycielList.forEach(p -> nauczycielComboBox.addItem(p.getId() + " " + p.getImie() + " " + p.getNazwisko()));
        }
        if (nauczycielComboBox.getSelectedItem() == null) {
            return;
        }
        String nauczycielCombo = nauczycielComboBox.getSelectedItem().toString();
        if (nauczycielCombo.isEmpty()) {
            log.info("nauczycielcombobox empty");
            return;
        }
        Nauczyciel nauczycielInList = findNauczycielInList(nauczycielCombo, nauczycielList);
        if (nauczycielInList == null) {
            log.info("nauczyciel cannot found in list");
            return;
        }
        imieTextField.setText(nauczycielInList.getImie());
        nazwiskoTextField.setText(nauczycielInList.getNazwisko());
        telefonTextField.setText(String.valueOf(nauczycielInList.getTelefon()));
        adresTextField.setText(nauczycielInList.getAdres());
        miastoTextField.setText(nauczycielInList.getMiasto().getNazwa());
        szkolaTextField.setText(nauczycielInList.getSzkola().getNazwa());
        stopienTextField.setText(nauczycielInList.getStopien());
        emailTextField.setText(nauczycielInList.getEmail());
    }

    private Nauczyciel findNauczycielInList(String selectedNauczyciel, List<Nauczyciel> nauczycielList) {
        String[] split = selectedNauczyciel.split(" ");
        Long idValue = Long.valueOf(split[0]);
        for (int i = 0; i < nauczycielList.size(); i++) {
            if (nauczycielList.get(i).getId().equals(idValue)) {
                return nauczycielList.get(i);
            }
        }
        return null;
    }

    private class NauczycielComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fillFiledInNauczycielTab();
        }

        private void fillFiledInNauczycielTab() {
            if (nauczycielComboBox.getSelectedIndex() == -1) {
                return;
            }
            String nauczycielCombo = nauczycielComboBox.getSelectedItem().toString();
            if (nauczycielCombo.isEmpty()) {
                log.info("nauczycielcombobox empty");
                return;
            }
            Nauczyciel nauczycielInList = findNauczycielInList(nauczycielCombo, nauczycielList);
            if (nauczycielInList == null) {
                log.info("nauczyciel cannot found in list");
                return;
            }
            imieTextField.setText(nauczycielInList.getImie());
            nazwiskoTextField.setText(nauczycielInList.getNazwisko());
            telefonTextField.setText(String.valueOf(nauczycielInList.getTelefon()));
            adresTextField.setText(nauczycielInList.getAdres());
            miastoTextField.setText(nauczycielInList.getMiasto().getNazwa());
            szkolaTextField.setText(nauczycielInList.getSzkola().getNazwa());
            stopienTextField.setText(nauczycielInList.getStopien());
            emailTextField.setText(nauczycielInList.getEmail());
        }
    }

    private class GrupaComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (grupaGrupaComboBox.getSelectedIndex() == -1) {
                return;
            }
            String grupaGrupaCombo = grupaGrupaComboBox.getSelectedItem().toString();
            if (grupaGrupaCombo.isEmpty()) {
                log.info("grupaGrupaCombo empty");
                return;
            }
            Grupa grupa = findInGrupGrupaList(grupaGrupaCombo);
            if (grupa == null) {
                log.info("grupa cannot found in grupalist");
                return;
            }
            grupaWiekTextField.setText(String.valueOf(grupa.getWiek()));
            Nauczyciel nauczyciel = grupa.getNauczyciel();
            grupaNauczycielTextField.setText(nauczyciel.getId() + " " + nauczyciel.getImie() + "" + nauczyciel.getNazwisko());
            grupaSalaTextField.setText(grupa.getSala().getNumerSali());
            List<Dziecko> dzieckoList = grupa.getDzieckoList();
            DefaultListModel<String> dzieciGrupaDefaultListModel = new DefaultListModel<>();
            dzieckoList.forEach(p -> dzieciGrupaDefaultListModel.addElement(p.getId() + " " + p.getImie()));
            grupaUczniowieList.setModel(dzieciGrupaDefaultListModel);
        }
    }

    private Grupa findInGrupGrupaList(String grupaGrupaCombo) {
        for (int i = 0; i < grupaList.size(); i++) {
            if (grupaList.get(i).getNazwa().equals(grupaGrupaCombo)) {
                return grupaList.get(i);
            }
        }
        return null;
    }


    private void fillGrupaGrupaComboBox() {
        grupaGrupaComboBox.removeAllItems();
        GrupaRepo grupaRepo = new GrupaRepo();
        grupaList = grupaRepo.getAll();
        if (grupaList == null) {
            log.info("Epmty list from db with grupa");
            return;
        }
        grupaList.forEach(p -> grupaGrupaComboBox.addItem(p.getNazwa()));
        if (grupaGrupaComboBox.getSelectedIndex() == -1) {
            return;
        }
        String grupaGrupaCombo = grupaGrupaComboBox.getSelectedItem().toString();
        if (grupaGrupaCombo.isEmpty()) {
            log.info("grupaGrupaCombo empty");
            return;
        }
        Grupa grupa = findInGrupGrupaList(grupaGrupaCombo);
        if (grupa == null) {
            log.info("grupa cannot found in grupalist");
            return;
        }
        grupaWiekTextField.setText(String.valueOf(grupa.getWiek()));
        Nauczyciel nauczyciel = grupa.getNauczyciel();
        grupaNauczycielTextField.setText(nauczyciel.getId() + " " + nauczyciel.getImie() + "" + nauczyciel.getNazwisko());
        grupaSalaTextField.setText(grupa.getSala().getNumerSali());
        List<Dziecko> dzieckoList = grupa.getDzieckoList();
        DefaultListModel<String> dzieciGrupaDefaultListModel = new DefaultListModel<>();
        dzieckoList.forEach(p -> dzieciGrupaDefaultListModel.addElement(p.getId() + " " + p.getImie()));
        grupaUczniowieList.setModel(dzieciGrupaDefaultListModel);
    }
}
