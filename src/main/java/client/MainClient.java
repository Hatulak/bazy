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
import java.util.LinkedList;
import java.util.List;

@Log
public class MainClient extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField dzieckoWiekTextField;
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
    private JTextField szafkaPojemnoscTextField;
    private JTextField szafkaHasloTextField;
    private JTextField szafkaDzieckoTextField;
    private JButton dodajSprzetButton;
    private JButton dodajZestawSprzetowButton;
    private JButton edytujZestawSprzetowButton;
    private JButton usunZestawSprzetowButton;
    private List<Miasto> miastoList;
    private List<Nauczyciel> nauczycielList;
    private List<Grupa> grupaList;
    private NauczycielRepo nauczycielRepo;

    public MainClient() {
        nauczycielRepo = new NauczycielRepo();
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
            Szkola szkola = szkolaRepo.getAll().get(0);
            nazwaSzkolaTextField.setText(szkola.getNazwa());
            adresSzkolaTextField.setText(szkola.getAdres());
            patronSzkolaTextField.setText(szkola.getPatron());
            miastoSzkolaTextField.setText(szkola.getMiasto().getNazwa());
            SalaRepo salaRepo = new SalaRepo();
            List<Sala> salaList = salaRepo.getAll();
            DefaultListModel salaListModel = new DefaultListModel();
            salaList.forEach(sala -> salaListModel.addElement(sala.getId() + " " + sala.getNumerSali()));
            saleSzkolaList.setModel(salaListModel);
            SzafkaRepo szafkaRepo = new SzafkaRepo();
            List<Szafka> szafkaList = szafkaRepo.getAll();
            DefaultListModel szafkaListModel = new DefaultListModel();
            szafkaList.forEach(szafka -> szafkaListModel.addElement(szafka.getId() + " " + szafka.getNumer()));
            szafkiSzkolaList.setModel(szafkaListModel);
            List<Nauczyciel> nauczyciele = nauczycielRepo.getAll();
            nauczycieleSzkolaList.removeAll();
            DefaultListModel nauczycieleListModel = new DefaultListModel();
            nauczyciele.forEach(e -> nauczycieleListModel.addElement(e.getId() + " " + e.getImie() + " " + e.getNazwisko()));
            nauczycieleSzkolaList.setModel(nauczycieleListModel);
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
        edytujGrupaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grupaGrupaComboBox.removeActionListener(grupaComboBoxListener);
                if (grupaGrupaComboBox.getSelectedItem().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Grupa is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Grupa inGrupGrupaList = findInGrupGrupaList(grupaGrupaComboBox.getSelectedItem().toString());
                if (inGrupGrupaList == null) {
                    log.info("found null in delete grupa button action");
                    return;
                }
                AddGrupaDialog addGrupaDialog = new AddGrupaDialog(inGrupGrupaList);
                addGrupaDialog.pack();
                addGrupaDialog.setVisible(true);
                fillGrupaGrupaComboBox();
                grupaGrupaComboBox.addActionListener(grupaComboBoxListener);
                refreshEverything();
            }
        });
        usunGrupaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grupaGrupaComboBox.removeActionListener(grupaComboBoxListener);
                if (grupaGrupaComboBox.getSelectedItem().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Grupa is empty!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Grupa inGrupGrupaList = findInGrupGrupaList(grupaGrupaComboBox.getSelectedItem().toString());
                if (inGrupGrupaList == null) {
                    log.info("found null in delete grupa button action");
                    return;
                }
                GrupaRepo grupaRepo = new GrupaRepo();
                grupaRepo.remove(inGrupGrupaList);
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
        edytujHaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalaSportowaRepo salaSportowaRepo = new SalaSportowaRepo();
                List<SalaSportowa> sportowaList = salaSportowaRepo.getAll();
                if (sportowaList == null) {
                    dodajHaleButton.setEnabled(true);
                    return;
                }
                if (sportowaList.isEmpty()) {
                    dodajHaleButton.setEnabled(true);
                    return;
                }
                SalaSportowa sportowa = sportowaList.get(0);
                AddHalaSportowaDialog addHalaSportowaDialog = new AddHalaSportowaDialog(sportowa);
                addHalaSportowaDialog.pack();
                addHalaSportowaDialog.setVisible(true);
                refreshEverything();
            }
        });
        usunHaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalaSportowaRepo salaSportowaRepo = new SalaSportowaRepo();
                List<SalaSportowa> sportowaList = salaSportowaRepo.getAll();
                if (sportowaList == null) {
                    dodajHaleButton.setEnabled(true);
                    return;
                }
                if (sportowaList.isEmpty()) {
                    dodajHaleButton.setEnabled(true);
                    return;
                }
                SalaSportowa sportowa = sportowaList.get(0);
                salaSportowaRepo.remove(sportowa);
                refreshEverything();
            }
        });
        dodajZestawSprzetowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalaSportowaRepo salaSportowaRepo = new SalaSportowaRepo();
                List<SalaSportowa> sportowaList = salaSportowaRepo.getAll();
                if (sportowaList == null) {
                    dodajHaleButton.setEnabled(true);
                    return;
                }
                if (sportowaList.isEmpty()) {
                    dodajHaleButton.setEnabled(true);
                    return;
                }
                SalaSportowa sportowa = sportowaList.get(0);
                AddZestawDialog addZestawDialog = new AddZestawDialog(sportowa, false, null);
                addZestawDialog.pack();
                addZestawDialog.setVisible(true);
                refreshEverything();
            }
        });
        dodajSprzetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSprzetDialog addSprzetDialog = new AddSprzetDialog();
                addSprzetDialog.pack();
                addSprzetDialog.setVisible(true);
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
                String id_imie = czesneDzieckoComboBox.getSelectedItem().toString();
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
        usunCzesneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List selectedValuesList = czesneCzesneList.getSelectedValuesList();
                List<String> czesneStringList = new LinkedList<>();
                selectedValuesList.forEach(s -> czesneStringList.add(s.toString()));
                List<Long> czesneIdList = new LinkedList<>();
                czesneStringList.forEach(c -> {
                    String id = new String();
                    int i = 0;
                    while (c.charAt(i) != ' ' && i < c.length()) {
                        id += c.charAt(i);
                        i++;
                    }
                    czesneIdList.add(Long.parseLong(id));
                });
                CzesneRepo czesneRepo = new CzesneRepo();
                czesneIdList.forEach(c -> {
                    Czesne byId = czesneRepo.getById(c);
                    czesneRepo.remove(byId);
                });
                refreshEverything();
            }
        });
        edytujCzesneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DzieckoRepo dzieckoRepo = new DzieckoRepo();
                String id_imie = czesneDzieckoComboBox.getSelectedItem().toString();
                String id = new String();
                int i = 0;
                while (id_imie.charAt(i) != ' ' && i < id_imie.length()) {
                    id += id_imie.charAt(i);
                    i++;
                }
                String czesneToEditString = czesneCzesneList.getSelectedValue().toString();
                i = 0;
                String idCzesne = new String();
                while (czesneToEditString.charAt(i) != ' ' && i < czesneToEditString.length()) {
                    idCzesne += czesneToEditString.charAt(i);
                    i++;
                }
                CzesneRepo czesneRepo = new CzesneRepo();
                Czesne czesne = czesneRepo.getById(Long.parseLong(idCzesne));

                Dziecko dziecko = dzieckoRepo.getById(Long.parseLong(id));
                AddCzesneDialog addCzesneDialog = new AddCzesneDialog(dziecko, czesne);
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
        czesneDzieckoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (czesneDzieckoComboBox.getSelectedItem() == null) {
                    edytujCzesneButton.setEnabled(false);
                    usunCzesneButton.setEnabled(false);
                    return;
                }
                DzieckoRepo dzieckoRepo = new DzieckoRepo();
                String id_imie = czesneDzieckoComboBox.getSelectedItem().toString();
                String id = new String();
                int i = 0;
                while (id_imie.charAt(i) != ' ' && i < id_imie.length()) {
                    id += id_imie.charAt(i);
                    i++;
                }
                Dziecko dziecko = dzieckoRepo.getById(Long.parseLong(id));

                CzesneRepo czesneRepo = new CzesneRepo();
                List<Czesne> czesneList = czesneRepo.getByDziecko(dziecko);

                DefaultListModel czesneListModel = new DefaultListModel();
                czesneList.forEach(c -> czesneListModel.addElement(c.getId() + " Kwota: " + c.getKwota()));
                czesneCzesneList.setModel(czesneListModel);
                usunCzesneButton.setEnabled(true);
                edytujCzesneButton.setEnabled(true);
            }
        });
        szafkaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (szafkaComboBox.getSelectedItem() == null) {
                    edytujSzafkeButton.setEnabled(false);
                    usunSzafkeButton.setEnabled(false);
                    return;
                }

                String id_numer = szafkaComboBox.getSelectedItem().toString();
                String id = new String();
                int i = 0;
                while (id_numer.charAt(i) != ' ' && i < id_numer.length()) {
                    id += id_numer.charAt(i);
                    i++;
                }
                SzafkaRepo szafkaRepo = new SzafkaRepo();
                Szafka szafka = szafkaRepo.getById(Long.parseLong(id));

                szafkaNumerTextField.setText(szafka.getNumer().toString());
                szafkaHasloTextField.setText(szafka.getHaslo());
                szafkaPojemnoscTextField.setText(szafka.getPojemnosc().toString());

                if (szafka.getDziecko() == null) {
                    szafkaDzieckoTextField.setText("BRAK PRZYPISANEGO DZIECKA");
                } else {
                    szafkaDzieckoTextField.setText(szafka.getDziecko().getId() + " Imie: " + szafka.getDziecko().getImie());
                }
                edytujSzafkeButton.setEnabled(true);
                usunSzafkeButton.setEnabled(true);
            }
        });
        usunSzafkeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_numer = szafkaComboBox.getSelectedItem().toString();
                String id = new String();
                int i = 0;
                while (id_numer.charAt(i) != ' ' && i < id_numer.length()) {
                    id += id_numer.charAt(i);
                    i++;
                }
                SzafkaRepo szafkaRepo = new SzafkaRepo();
                Szafka szafka = szafkaRepo.getById(Long.parseLong(id));
                szafkaRepo.remove(szafka);
                refreshEverything();
            }
        });
        edytujSzafkeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_numer = szafkaComboBox.getSelectedItem().toString();
                String id = new String();
                int i = 0;
                while (id_numer.charAt(i) != ' ' && i < id_numer.length()) {
                    id += id_numer.charAt(i);
                    i++;
                }
                SzafkaRepo szafkaRepo = new SzafkaRepo();
                Szafka szafka = szafkaRepo.getById(Long.parseLong(id));

                AddSzafkaDialog addSzafkaDialog = new AddSzafkaDialog(szafka);
                addSzafkaDialog.pack();
                addSzafkaDialog.setVisible(true);
                refreshEverything();
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
        edytujZestawSprzetowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalaSportowaRepo salaSportowaRepo = new SalaSportowaRepo();
                List<SalaSportowa> sportowaList = salaSportowaRepo.getAll();
                if (sportowaList == null) {
                    dodajHaleButton.setEnabled(true);
                    return;
                }
                if (sportowaList.isEmpty()) {
                    dodajHaleButton.setEnabled(true);
                    return;
                }
                if (zestawySprzetowList.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "No item is selected from list!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String selectedZestaw = zestawySprzetowList.getSelectedValue().toString();
                ZestawSprzetowRepo zestawSprzetowRepo = new ZestawSprzetowRepo();
                List<ZestawSprzetow> zestawSprzetow = zestawSprzetowRepo.getAll();
                if (zestawSprzetow == null) {
                    JOptionPane.showMessageDialog(null, "Problem with get zestawsprzetow from DB!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (zestawSprzetow.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Problem with get zestawsprzetow from DB!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String idText = selectedZestaw.split(" ")[0];
                Long id = Long.valueOf(idText);
                ZestawSprzetow zestawSprzetowToEdit = null;
                for (int i = 0; i < zestawSprzetow.size(); i++) {
                    if (zestawSprzetow.get(i).getId().equals(id)) {
                        zestawSprzetowToEdit = zestawSprzetow.get(i);
                        break;
                    }
                }
                if (zestawSprzetowToEdit == null) {
                    JOptionPane.showMessageDialog(null, "Problem with get zestawsprzetow from DB!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                SalaSportowa sportowa = sportowaList.get(0);
                AddZestawDialog addZestawDialog = new AddZestawDialog(sportowa, true, zestawSprzetowToEdit);
                addZestawDialog.pack();
                addZestawDialog.setVisible(true);
                refreshEverything();
            }
        });
        usunZestawSprzetowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (zestawySprzetowList.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "No item is selected from list!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String selectedZestaw = zestawySprzetowList.getSelectedValue().toString();
                ZestawSprzetowRepo zestawSprzetowRepo = new ZestawSprzetowRepo();
                List<ZestawSprzetow> zestawSprzetow = zestawSprzetowRepo.getAll();
                if (zestawSprzetow == null) {
                    JOptionPane.showMessageDialog(null, "Problem with get zestawsprzetow from DB!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (zestawSprzetow.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Problem with get zestawsprzetow from DB!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String idText = selectedZestaw.split(" ")[0];
                Long id = Long.valueOf(idText);
                ZestawSprzetow zestawSprzetowToRemove = null;
                for (int i = 0; i < zestawSprzetow.size(); i++) {
                    if (zestawSprzetow.get(i).getId().equals(id)) {
                        zestawSprzetowToRemove = zestawSprzetow.get(i);
                        break;
                    }
                }
                if (zestawSprzetowToRemove == null) {
                    JOptionPane.showMessageDialog(null, "Problem with get zestawsprzetow from DB!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                SalaSportowa salaSportowa = zestawSprzetowToRemove.getSalaSportowa();
                salaSportowa.removeZestawSprzetow(zestawSprzetowToRemove);
                new SalaSportowaRepo().update(salaSportowa);
                zestawSprzetowToRemove.setSalaSportowa(null);
                zestawSprzetowRepo.update(zestawSprzetowToRemove);
                zestawSprzetowRepo.remove(zestawSprzetowToRemove);
                refreshEverything();
            }
        });
        edytujDzieckoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Edycja dziecka
            }
        });
        usunDzieckoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dzieckoGrupaCombo = dzieckoGrupaComboBox.getSelectedItem().toString();
                String dzieckoUczenCombo = dzieckoUczenComboBox.getSelectedItem().toString();
                if (dzieckoGrupaCombo.isEmpty() || dzieckoUczenCombo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty combobox!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Grupa grupaDB = findInGrupGrupaList(dzieckoGrupaCombo);
                if (grupaDB == null) {
                    JOptionPane.showMessageDialog(null, "Problem with DB!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Dziecko dzieckoDB = findInDbDziecko(dzieckoUczenCombo);
                if (dzieckoDB == null) {
                    JOptionPane.showMessageDialog(null, "Problem with DB!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                GrupaRepo grupaRepo = new GrupaRepo();
                DzieckoRepo dzieckoRepo = new DzieckoRepo();
                SzafkaRepo szafkaRepo = new SzafkaRepo();
                Szafka szafkaDB = findSzafkaOfDziecko(dzieckoDB);
                if (szafkaDB != null) {
                    szafkaDB.setDziecko(null);
                    szafkaRepo.update(szafkaDB);
                }
                grupaDB.removeDziecko(dzieckoDB);
                dzieckoDB.setRodzicSet(null);
                dzieckoDB.setGrupa(null);
                grupaRepo.update(grupaDB);
                dzieckoRepo.update(dzieckoDB);
                dzieckoRepo.remove(dzieckoDB);
                refreshEverything();
                //TODO Usuwanie dziecka
            }
        });
        dzieckoGrupaComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                dzieckoUczenComboBox.removeAllItems();
                GrupaRepo grupaRepo = new GrupaRepo();
                Grupa currentGrupa = grupaRepo.getById(Long.parseLong(dzieckoGrupaComboBox.getSelectedItem().toString().split(" ")[0]));
                dzieckoUczenComboBox.setEnabled(true);
                currentGrupa.getDzieckoList().forEach(dziecko -> dzieckoUczenComboBox.addItem(dziecko.getId() + " " + dziecko.getImie()));
                fillDzieckoWindow(dzieckoUczenComboBox.getSelectedItem().toString());
            }
        });
        ActionListener dzieckoUczenActionListener = new DzieckoComboBoxListener();
        dzieckoUczenComboBox.addActionListener(dzieckoUczenActionListener);
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
            if (szafkaListDB.get(i).getDziecko().getId().equals(dzieckoDB.getId())) {
                return szafkaListDB.get(i);
            }
        }
        return null;
    }

    private Dziecko findInDbDziecko(String dzieckoUczenCombo) {
        Long id = Long.valueOf(dzieckoUczenCombo.split(" ")[0]);
        Dziecko byId = new DzieckoRepo().getById(id);
        return byId;
    }

    private void fillDzieckoWindow(String s) {
        if (dzieckoUczenComboBox.getSelectedIndex() == -1) {
            return;
        }
        DzieckoRepo dzieckoRepo = new DzieckoRepo();
        Dziecko dziecko = dzieckoRepo.getById(Long.parseLong(s.split(" ")[0]));
        dzieckoWiekTextField.setText(String.valueOf(dziecko.getWiek()));
        dzieckoImieTextField.setText(dziecko.getImie());
        DefaultListModel dzieckoModel = new DefaultListModel();
        dziecko.getRodzicSet().forEach(rodzic -> dzieckoModel.addElement(rodzic.getId() + " " + rodzic.getImie() + " " + rodzic.getNazwisko()));
        dzieckoRodziceList.setModel(dzieckoModel);
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
        SalaSportowaRepo salaSportowaRepo = new SalaSportowaRepo();
        SzkolaRepo szkolaRepo = new SzkolaRepo();

        List<Dziecko> dzieci = dzieckoRepo.getAll();
        List<Grupa> grupy = grupaRepo.getAll();
        List<Miasto> miasta = miastoRepo.getAll();
        List<Nauczyciel> nauczyciele = nauczycielRepo.getAll();
        List<Sala> sale = salaRepo.getAll();
        List<Szafka> szafki = szafkaRepo.getAll();
        List<SalaSportowa> salaSportowaList = salaSportowaRepo.getAll();
        Szkola szkola = szkolaRepo.getAll().get(0);

        nazwaSzkolaTextField.setText(szkola.getNazwa());
        adresSzkolaTextField.setText(szkola.getAdres());
        patronSzkolaTextField.setText(szkola.getPatron());
        miastoSzkolaTextField.setText(szkola.getMiasto().getNazwa());

        DefaultListModel salaListModel = new DefaultListModel();
        DefaultListModel szafkaListModel = new DefaultListModel();
        DefaultListModel nauczycieleListModel = new DefaultListModel();

        NauczycielComboBoxListener nauczycielComboBoxListener = new NauczycielComboBoxListener();
        MiastoComboBoxActionListener miastoComboBoxActionListener = new MiastoComboBoxActionListener();
        GrupaComboBoxListener grupaComboBoxListener = new GrupaComboBoxListener();
        DzieckoComboBoxListener dzieckoComboBoxListener = new DzieckoComboBoxListener();

        dzieckoUczenComboBox.removeActionListener(dzieckoComboBoxListener);
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
            dzieckoGrupaComboBox.addItem(i.getId() + " " + i.getNazwa());
            grupaGrupaComboBox.addItem(i.getId() + " " + i.getNazwa());
        });
        nauczyciele.forEach(i -> {
            nauczycieleListModel.addElement(i.getId() + " " + i.getImie() + " " + i.getNazwisko());
            nauczycielComboBox.addItem(String.format("%s %s %s", i.getId(), i.getImie(), i.getNazwisko()));
        });
        sale.forEach(i -> {
            salaComboBox.addItem(i.getNumerSali());
            salaListModel.addElement(i.getNumerSali());
        });
        szafki.forEach(i -> {
            szafkaComboBox.addItem(i.getId() + " Numer Szafki: " + i.getNumer());
            szafkaListModel.addElement(i.getId() + " Numer Szafki: " + i.getNumer());
        });
        szafkiSzkolaList.setModel(szafkaListModel);
        nauczycieleSzkolaList.setModel(nauczycieleListModel);
        saleSzkolaList.setModel(salaListModel);

        dzieckoUczenComboBox.addActionListener(dzieckoComboBoxListener);
        grupaGrupaComboBox.addActionListener(grupaComboBoxListener);
        nauczycielComboBox.addActionListener(nauczycielComboBoxListener);
        miastoComboBox.addItemListener(miastoComboBoxActionListener);
        fillHalaTab(salaSportowaList);
    }

    private void fillHalaTab(List<SalaSportowa> salaSportowaList) {
        if (salaSportowaList == null) {
            dodajHaleButton.setEnabled(true);
            edytujHaleButton.setEnabled(false);
            wielkoscTextField.setText("");
            trybunaCheckBox.setSelected(false);
            DefaultListModel<String> zestawSprzetowDefaultListModel = new DefaultListModel<>();
            zestawySprzetowList.setModel(zestawSprzetowDefaultListModel);
            dodajZestawSprzetowButton.setEnabled(false);
            dodajSprzetButton.setEnabled(false);
            usunZestawSprzetowButton.setEnabled(false);
            edytujZestawSprzetowButton.setEnabled(false);
            return;
        }
        if (salaSportowaList.isEmpty()) {
            wielkoscTextField.setText("");
            trybunaCheckBox.setSelected(false);
            edytujHaleButton.setEnabled(false);
            dodajHaleButton.setEnabled(true);
            dodajSprzetButton.setEnabled(false);
            DefaultListModel<String> zestawSprzetowDefaultListModel = new DefaultListModel<>();
            zestawySprzetowList.setModel(zestawSprzetowDefaultListModel);
            dodajZestawSprzetowButton.setEnabled(false);
            usunZestawSprzetowButton.setEnabled(false);
            edytujZestawSprzetowButton.setEnabled(false);
            return;
        }
        SalaSportowa salaSportowa = salaSportowaList.get(0);
        dodajHaleButton.setEnabled(false);
        edytujHaleButton.setEnabled(true);
        dodajZestawSprzetowButton.setEnabled(true);
        wielkoscTextField.setText(String.valueOf(salaSportowa.getWielkosc()));
        trybunaCheckBox.setSelected(salaSportowa.getCzyTrybuna());
        DefaultListModel<String> zestawSprzetowDefaultListModel = new DefaultListModel<>();
        salaSportowa.getZestawSprzetowList().forEach(p -> zestawSprzetowDefaultListModel.addElement(p.getId() + " " + p.getDyscyplina()));
        zestawySprzetowList.setModel(zestawSprzetowDefaultListModel);
        if (!salaSportowa.getZestawSprzetowList().isEmpty()) {
            dodajSprzetButton.setEnabled(true);
            usunZestawSprzetowButton.setEnabled(true);
            edytujZestawSprzetowButton.setEnabled(true);
        }
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

    private class DzieckoComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fillDzieckoWindow(dzieckoUczenComboBox.getSelectedItem().toString());
        }
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
        Long id = Long.valueOf(grupaGrupaCombo.split(" ")[0]);
        for (int i = 0; i < grupaList.size(); i++) {
            if (grupaList.get(i).getId().equals(id)) {
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
        grupaList.forEach(p -> grupaGrupaComboBox.addItem(p.getId() + " " + p.getNazwa()));
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
