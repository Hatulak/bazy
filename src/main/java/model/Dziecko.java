package model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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
    @OneToMany(mappedBy = "dziecko", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Czesne> czesneList;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Dziecko_Rodzic",
            joinColumns = {@JoinColumn(name = "DZIECKO_ID")},
            inverseJoinColumns = {@JoinColumn(name = "RODZIC_ID")}
    )
    Set<Rodzic> rodzicSet = new HashSet<>();

    public Dziecko() {
    }

    public Dziecko(String imie, Integer wiek, Grupa grupa, List<Czesne> czesneList, Set<Rodzic> rodzicSet) {
        this.imie = imie;
        this.wiek = wiek;
        this.grupa = grupa;
        this.czesneList = czesneList;
        this.rodzicSet = rodzicSet;
    }

    public void addRodzic(Rodzic rodzic) {
        rodzicSet.add(rodzic);
    }

    public void removeRodzic(Rodzic rodzic) {
        rodzicSet.remove(rodzic);
    }

    public void addCzesne(Czesne czesne) {
        czesneList.add(czesne);
    }

    public void removeCzesne(Czesne czesne) {
        czesneList.remove(czesne);
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

    public List<Czesne> getCzesneList() {
        return czesneList;
    }

    public void setCzesneList(List<Czesne> czesneList) {
        this.czesneList = czesneList;
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
