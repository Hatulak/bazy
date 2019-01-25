package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SALA")
public class Sala {
    @Id
    @GeneratedValue
    @Column(name = "SALA_ID", nullable = false)
    private Long id;
    private String numerSali;
    private Integer liczbaKrzesel;
    private Integer liczbaLawek;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SZKOLA_ID")
    private Szkola szkola;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RZUTNIK_ID")
    private Rzutnik rzutnik;
    @OneToMany(mappedBy = "sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Komputer> komputerList;

    public Sala() {
    }

    public Sala(String numerSali, Integer liczbaKrzesel, Integer liczbaLawek, Szkola szkola, Rzutnik rzutnik) {
        this.numerSali = numerSali;
        this.liczbaKrzesel = liczbaKrzesel;
        this.liczbaLawek = liczbaLawek;
        this.szkola = szkola;
        this.rzutnik = rzutnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLiczbaKrzesel() {
        return liczbaKrzesel;
    }

    public void setLiczbaKrzesel(Integer liczbaKrzesel) {
        this.liczbaKrzesel = liczbaKrzesel;
    }

    public Integer getLiczbaLawek() {
        return liczbaLawek;
    }

    public void setLiczbaLawek(Integer liczbaLawek) {
        this.liczbaLawek = liczbaLawek;
    }

    public Szkola getSzkola() {
        return szkola;
    }

    public void setSzkola(Szkola szkola) {
        this.szkola = szkola;
    }

    public Rzutnik getRzutnik() {
        return rzutnik;
    }

    public void setRzutnik(Rzutnik rzutnik) {
        this.rzutnik = rzutnik;
    }

    public List<Komputer> getKomputerList() {
        return komputerList;
    }

    public void setKomputerList(List<Komputer> komputerList) {
        this.komputerList = komputerList;
    }

    public String getNumerSali() {
        return numerSali;
    }

    public void setNumerSali(String numerSali) {
        this.numerSali = numerSali;
    }
}
