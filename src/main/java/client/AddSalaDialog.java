package client;

import Repository.KomputerRepo;
import Repository.RzutnikRepo;
import Repository.SalaRepo;
import Repository.SzkolaRepo;
import lombok.extern.java.Log;
import model.Komputer;
import model.Rzutnik;
import model.Sala;
import model.Szkola;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
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
    private List<Komputer> komputeryWSaliToEditList;
    private List<Szkola> szkolaList;
    private Sala sala;
    private Rzutnik rzutnikInSala;

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
        fillComboboxSzkola();
    }

    public AddSalaDialog(Sala sala) {
        this.sala = sala;
        this.rzutnikInSala = sala.getRzutnik();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        stworzRzutnikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRzutnikDialog addRzutnikDialog = new AddRzutnikDialog();
                addRzutnikDialog.pack();
                addRzutnikDialog.setVisible(true);
                fillComboboxRzutnikToEdit();
            }
        });
        stworzKomputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddKomputerDialog addKomputerDialog = new AddKomputerDialog();
                addKomputerDialog.pack();
                addKomputerDialog.setVisible(true);
                fillListViewKomupterToEdit();
            }
        });
        fillComboboxRzutnikToEdit();
        fillListViewKomupterToEdit();
        fillComboboxSzkola();

        komputeryWSaliToEditList.forEach(k -> {
            komputeryJList.setSelectedValue(k.getId(), true);
            komputeryJList.getSelectionModel().setSelectionInterval(0, 2);
        });
        numerTextField.setText(sala.getNumerSali());
        liczbaKrzeselTextField.setText(sala.getLiczbaKrzesel().toString());
        liczbaLawekTextField.setText(sala.getLiczbaLawek().toString());
    }

    private void onEditOK() {
        String numerSali = numerTextField.getText();
        Integer liczbaKrzesel = Integer.parseInt(liczbaKrzeselTextField.getText());
        Integer liczbaLawek = Integer.parseInt(liczbaLawekTextField.getText());

        SzkolaRepo szkolaRepo = new SzkolaRepo();
        List<Szkola> szkolaList = szkolaRepo.getByName(szkolaComboBox.getSelectedItem().toString());
        Szkola szkola = szkolaList.get(0);

        RzutnikRepo rzutnikRepo = new RzutnikRepo();
        List<Rzutnik> rzutnikList = rzutnikRepo.getByModel(rzutnikComboBox.getSelectedItem().toString());
        Rzutnik rzutnik = rzutnikList.get(0);

        KomputerRepo komputerRepo = new KomputerRepo();
        List selectedValuesList = komputeryJList.getSelectedValuesList();
        List<Komputer> selectedComputerList = new LinkedList<>();
        for (int i = 0; i < selectedValuesList.size(); i++) {
            selectedComputerList.add(komputerRepo.getById(Long.parseLong(selectedValuesList.get(i).toString())));
        }

        SalaRepo salaRepo = new SalaRepo();
        sala.setNumerSali(numerSali);
        sala.setLiczbaKrzesel(liczbaKrzesel);
        sala.setLiczbaLawek(liczbaLawek);
        sala.setSzkola(szkola);
        sala.setRzutnik(rzutnik);
        salaRepo.update(sala);

        komputeryWSaliToEditList.forEach(k -> {
            k.setSala(null);
            komputerRepo.update(k);
        });


        selectedComputerList.forEach(c -> {
            c.setSala(sala);
            komputerRepo.update(c);
        });

        dispose();
    }

    private void fillListViewKomupterToEdit() {
        komputeryJList.removeAll();
        KomputerRepo komputerRepo = new KomputerRepo();
        komputerList = komputerRepo.getAllKomputersWhereSalaIdIsNull();
        komputeryWSaliToEditList = komputerRepo.getBySala(sala);

        DefaultListModel komputerListModel = new DefaultListModel();

        if (komputeryWSaliToEditList == null) {
            log.info("Empty list with komputer's get from DB");
        } else {
            komputeryWSaliToEditList.forEach(k -> komputerListModel.addElement(k.getId().toString()));
        }

        if (komputerList == null) {
            log.info("Empty list with komputer's get from DB");
        } else {
            komputerList.forEach(e -> komputerListModel.addElement(e.getId().toString()));
        }

        komputeryJList.setModel(komputerListModel);
    }

    private void fillComboboxRzutnikToEdit() {
        rzutnikComboBox.removeAllItems();
        RzutnikRepo rzutnikRepo = new RzutnikRepo();
        rzutnikList = rzutnikRepo.getRzutniksWhereRzutnikInSalaIsNull();
        rzutnikComboBox.addItem(rzutnikInSala.getModel());
        if (rzutnikList == null) {
            log.info("Empty list with rzutnik's get from DB");
        } else {
            rzutnikList.forEach(p -> rzutnikComboBox.addItem(p.getModel()));
        }
    }


    private void fillListViewKomupter() {
        komputeryJList.removeAll();
        KomputerRepo komputerRepo = new KomputerRepo();
        komputerList = komputerRepo.getAllKomputersWhereSalaIdIsNull();
        if (komputerList == null) {
            log.info("Empty list with komputer's get from DB");
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
            log.info("Empty list with rzutnik's get from DB");
        } else {
            rzutnikList.forEach(p -> rzutnikComboBox.addItem(p.getModel()));
        }
    }

    private void fillComboboxSzkola() {
        szkolaComboBox.removeAllItems();
        SzkolaRepo szkolaRepo = new SzkolaRepo();
        szkolaList = szkolaRepo.getAll();
        if (szkolaList == null) {
            log.info("Empty list with szkola's get from DB");
        } else {
            szkolaList.forEach(s -> szkolaComboBox.addItem(s.getNazwa()));
        }
    }


    private void onOK() {
        String numerSali = numerTextField.getText();
        Integer liczbaKrzesel = Integer.parseInt(liczbaKrzeselTextField.getText());
        Integer liczbaLawek = Integer.parseInt(liczbaLawekTextField.getText());

        SzkolaRepo szkolaRepo = new SzkolaRepo();
        List<Szkola> szkolaList = szkolaRepo.getByName(szkolaComboBox.getSelectedItem().toString());
        Szkola szkola = szkolaList.get(0);

        RzutnikRepo rzutnikRepo = new RzutnikRepo();
        List<Rzutnik> rzutnikList = rzutnikRepo.getByModel(rzutnikComboBox.getSelectedItem().toString());
        Rzutnik rzutnik = rzutnikList.get(0);

        KomputerRepo komputerRepo = new KomputerRepo();
        List selectedValuesList = komputeryJList.getSelectedValuesList();
        List<Komputer> selectedComputerList = new LinkedList<>();
        for (int i = 0; i < selectedValuesList.size(); i++) {
            selectedComputerList.add(komputerRepo.getById(Long.parseLong(selectedValuesList.get(i).toString())));
        }

        SalaRepo salaRepo = new SalaRepo();
        Sala sala = new Sala(numerSali, liczbaKrzesel, liczbaLawek, szkola, rzutnik);
        salaRepo.save(sala);

        selectedComputerList.forEach(c -> {
            c.setSala(sala);
            komputerRepo.update(c);
        });

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
