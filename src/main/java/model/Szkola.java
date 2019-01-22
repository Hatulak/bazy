package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SZKOLA")
public class Szkola {
    @Id
    @GeneratedValue
    @Column(name = "SZKOLA_ID", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "MIASTO_ID")
    private Miasto miasto;
    private String nazwa;
    private String patron;
    private String adres;
    @OneToMany(mappedBy = "szkola", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sala> salaList;
    @OneToMany(mappedBy = "szkola", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Szafka> szafkaLista;
    @OneToMany(mappedBy = "szkola", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Nauczyciel> nauczycielList;

    public Szkola() {
    }

    public Szkola(Miasto miasto, String nazwa, String patron, String adres, List<Sala> salaList, List<Szafka> szafkaLista) {
        this.miasto = miasto;
        this.nazwa = nazwa;
        this.patron = patron;
        this.adres = adres;
        this.salaList = salaList;
        this.szafkaLista = szafkaLista;
    }

    public Szkola(Miasto miasto, String nazwa, String patron, String adres, List<Sala> salaList, List<Szafka> szafkaLista, List<Nauczyciel> nauczycielList) {
        this.miasto = miasto;
        this.nazwa = nazwa;
        this.patron = patron;
        this.adres = adres;
        this.salaList = salaList;
        this.szafkaLista = szafkaLista;
        this.nauczycielList = nauczycielList;
    }

    public void addNauczyciel(Nauczyciel nauczyciel) {
        nauczycielList.add(nauczyciel);
    }

    public void removeNauczyciel(Nauczyciel nauczyciel) {
        nauczycielList.remove(nauczyciel);
    }

    public void addSzafka(Szafka szafka) {
        szafkaLista.add(szafka);
    }

    public void removeSzafka(Szafka szafka) {
        szafkaLista.remove(szafka);
    }

    public void addSala(Sala sala) {
        salaList.add(sala);
    }

    public void removeSala(Sala sala) {
        salaList.remove(sala);
    }

    public List<Nauczyciel> getNauczycielList() {
        return nauczycielList;
    }

    public void setNauczycielList(List<Nauczyciel> nauczycielList) {
        this.nauczycielList = nauczycielList;
    }

    public List<Szafka> getSzafkaLista() {
        return szafkaLista;
    }

    public void setSzafkaLista(List<Szafka> szafkaLista) {
        this.szafkaLista = szafkaLista;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Miasto getMiasto() {
        return miasto;
    }

    public void setMiasto(Miasto miasto) {
        this.miasto = miasto;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public List<Sala> getSalaList() {
        return salaList;
    }

    public void setSalaList(List<Sala> salaList) {
        this.salaList = salaList;
    }
}
