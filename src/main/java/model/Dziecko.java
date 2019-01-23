package model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "DZIECKO")
public class Dziecko {
    @Id
    @GeneratedValue
    @Column(name = "DZIECKO_ID", nullable = false)
    private Long id;
    private String imie;
    private Integer wiek;
    @ManyToOne
    @JoinColumn(name = "GRUPA_ID")
    private Grupa grupa;
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "Dziecko_Rodzic",
            joinColumns = {@JoinColumn(name = "DZIECKO_ID")},
            inverseJoinColumns = {@JoinColumn(name = "RODZIC_ID")}
    )
    Set<Rodzic> rodzicSet = new HashSet<>();

    public Dziecko() {
    }

    public Dziecko(String imie, Integer wiek, Grupa grupa, Set<Rodzic> rodzicSet) {
        this.imie = imie;
        this.wiek = wiek;
        this.grupa = grupa;
        this.rodzicSet = rodzicSet;
    }

    public void addRodzic(Rodzic rodzic) {
        rodzicSet.add(rodzic);
    }

    public void removeRodzic(Rodzic rodzic) {
        rodzicSet.remove(rodzic);
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

    public Integer getWiek() {
        return wiek;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }


    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public Set<Rodzic> getRodzicSet() {
        return rodzicSet;
    }

    public void setRodzicSet(Set<Rodzic> rodzicSet) {
        this.rodzicSet = rodzicSet;
    }

    @Override
    public String toString() {
        return "Dziecko{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", wiek=" + wiek +
                ", grupa=" + grupa +
                '}';
    }
}
