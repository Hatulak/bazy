package model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "GRUPA")
public class Grupa {
    @Id
    @GeneratedValue
    @Column(name = "GRUPA_ID", nullable = false)
    private Long id;
    private String nazwa;
    private Integer maxLiczbaDzieci;
    private Integer wiek;
    @OneToOne
    @JoinColumn(name = "NAUCZYCIEL_ID")
    private Nauczyciel nauczyciel;
    @OneToOne
    @JoinColumn(name = "SALA_ID")
    private Sala sala;
    @OneToMany(mappedBy = "grupa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Dziecko> dzieckoList;

    public Grupa() {
    }

    public Grupa(Integer maxLiczbaDzieci, Integer wiek, Nauczyciel nauczyciel, Sala sala, List<Dziecko> dzieckoList) {
        this.maxLiczbaDzieci = maxLiczbaDzieci;
        this.wiek = wiek;
        this.nauczyciel = nauczyciel;
        this.sala = sala;
        this.dzieckoList = dzieckoList;
    }

    public void addDziecko(Dziecko dziecko) {
        dzieckoList.add(dziecko);
    }

    public void removeDziecko(Dziecko dziecko) {
        dzieckoList.remove(dziecko);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxLiczbaDzieci() {
        return maxLiczbaDzieci;
    }

    public void setMaxLiczbaDzieci(Integer maxLiczbaDzieci) {
        this.maxLiczbaDzieci = maxLiczbaDzieci;
    }

    public Integer getWiek() {
        return wiek;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<Dziecko> getDzieckoList() {
        return dzieckoList;
    }

    public void setDzieckoList(List<Dziecko> dzieckoList) {
        this.dzieckoList = dzieckoList;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
