package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClient extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField dzieckoWiekTextField;
    private JTextField dzieckoNazwiskoTextField;
    private JTextField dzieckoImieTextField;
    private JList dzieckoRodziceList;
    private JList dzieckoCzesneList;
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
    private JComboBox szkolaComboBox;
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
    private JTextField textField1;

    public MainClient() {
        add(panel1);
        setTitle("School app");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 400));
        pack();
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
    }
}
