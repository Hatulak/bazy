package model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RODZIC")
public class Rodzic {
    @Id
    @GeneratedValue
    @Column(name = "RODZIC_ID", nullable = false)
    private Long id;
    private String imie;
    private String nazwisko;
    @OneToOne
    @JoinColumn(name = "MIASTO_ID")
    private Miasto miasto;
    private String adres;
    private Integer telefon;
    @ManyToMany(mappedBy = "rodzicSet")
    Set<Dziecko> dzieckoSet = new HashSet<>();

    public Rodzic() {
    }

    public Rodzic(String imie, String nazwisko, Miasto miasto, String adres, Integer telefon) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.miasto = miasto;
        this.adres = adres;
        this.telefon = telefon;
    }

    public void addDziecko(Dziecko dziecko) {
        dzieckoSet.add(dziecko);
    }

    public void removeDziecko(Dziecko dziecko) {
        dzieckoSet.remove(dziecko);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Miasto getMiasto() {
        return miasto;
    }

    public void setMiasto(Miasto miasto) {
        this.miasto = miasto;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public Set<Dziecko> getDzieckoSet() {
        return dzieckoSet;
    }

    public void setDzieckoSet(Set<Dziecko> dzieckoSet) {
        this.dzieckoSet = dzieckoSet;
    }
}

