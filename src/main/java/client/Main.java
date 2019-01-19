package client;

import model.*;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;

public class Main {

    private static EntityManager em;

    public static void main(String[] args) {
////        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sbdPU");
////        em = entityManagerFactory.createEntityManager();
////        em.getTransaction().begin();
////
////        Miasto miasto = new Miasto("Zambrow","Zambrow","zambrowski","podlaskie");
////
//////        Rodzic rodzic = new Rodzic("Jakub",miasto,"Bialostocka 57b",0,123456789,)
////
////
//////        Dziecko dziecko = new Dziecko("Adam",10);
////
//////        Czesne czesne = new Czesne();
////
////        em.persist(miasto);
////        em.getTransaction().commit();
////        session = EMF.getSessionFactory().openSession();
////        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
////        EntityManager em = entityManagerFactory.createEntityManager();
////
////
//        CzesneRepo czesneRepo = new CzesneRepo();
////        Miasto miasto = new Miasto("Ełk", "ełk", "Ełk", "war-mar");
////        Szkola szkola = new Szkola(miasto, "nazwa", "patron", "adress", new ArrayList<Sala>(), new ArrayList<Szafka>());
////        Nauczyciel nauczyciel = new Nauczyciel("michał", "złościk", "exo@w.pl", 608772, "jakis", miasto, "adres", szkola);
////        Rodzic rodzic = new Rodzic("Maria", "Polejczuk", miasto, "adress", 66);
////        Rzutnik rzutnik = new Rzutnik("model", "jakosc", new Date(), new Date());
////        Sala sala = new Sala(15, 15, szkola, rzutnik, new ArrayList<Komputer>());
////        Grupa grupa = new Grupa(15, 14, nauczyciel, sala, new ArrayList<Dziecko>());
////        Dziecko dziecko = new Dziecko("adam", 14, grupa, new ArrayList<Czesne>(), new HashSet<Rodzic>());
////        grupa.addDziecko(dziecko);
////        rodzic.addDziecko(dziecko);
////        szkola.addSala(sala);
////        Czesne czesne = new Czesne(dziecko, new Date());
////        dziecko.addCzesne(czesne);
////        em.getTransaction().begin();
////        em.persist(czesne);
////        em.persist(dziecko);
////        em.persist(rodzic);
////        em.persist(miasto);
////        em.persist(grupa);
////        em.persist(sala);
////        em.persist(rzutnik);
////        em.persist(szkola);
////        em.persist(nauczyciel);
////        em.getTransaction().commit();
////        em.close();
////        Czesne czesne2 = new Czesne(dziecko, new Date());
////        czesneRepo.save(czesne2);
//        Czesne czesne = czesneRepo.getById(11L);
//        System.out.println(czesne.toString());
//
//        List<Czesne> all = czesneRepo.getAll();
//        all.forEach(System.out::println);

        JTabbedPane tabbedPane = new JTabbedPane();
        JFrame mainFrame = new JFrame("School app");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocation(10, 10);
        mainFrame.setPreferredSize(new Dimension(300, 300));
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.add(tabbedPane);
        createTabs(tabbedPane);
        tabbedPane.setVisible(true);
    }

    private static void createTabs(JTabbedPane tabbedPane) {
        tabbedPane.addTab("Dziecko", new DzieckoTab().getPanel1());
        tabbedPane.addTab("Grupa", new GrupaTab().getPanel1());
        tabbedPane.addTab("Nauczyciel", new NauczycielTab().getPanel1());
        tabbedPane.addTab("Sala", new SalaTab().getPanel1());
        tabbedPane.addTab("Szkoła", new SzkolaTab().getPanel1());
    }

}
