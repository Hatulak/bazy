package model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "SALASPORTOWA")
public class SalaSportowa {
    @Id
    @GeneratedValue
    @Column(name = "SALASPORTOWA_ID", nullable = false)
    private Long id;
    private Integer wielkosc;
    private Boolean czyTrybuna;
    @OneToOne
    @JoinColumn(name = "SZKOLA_ID")
    private Szkola szkola;
    @OneToMany(mappedBy = "salaSportowa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ZestawSprzetow> zestawSprzetowList;

    public SalaSportowa() {
    }

    public SalaSportowa(Integer wielkosc, Boolean czyTrybuna, Szkola szkola, List<ZestawSprzetow> zestawSprzetowList) {
        this.wielkosc = wielkosc;
        this.czyTrybuna = czyTrybuna;
        this.szkola = szkola;
        this.zestawSprzetowList = zestawSprzetowList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWielkosc() {
        return wielkosc;
    }

    public void setWielkosc(Integer wielkosc) {
        this.wielkosc = wielkosc;
    }

    public Boolean getCzyTrybuna() {
        return czyTrybuna;
    }

    public void setCzyTrybuna(Boolean czyTrybuna) {
        this.czyTrybuna = czyTrybuna;
    }

    public Szkola getSzkola() {
        return szkola;
    }

    public void setSzkola(Szkola szkola) {
        this.szkola = szkola;
    }

    public List<ZestawSprzetow> getZestawSprzetowList() {
        return zestawSprzetowList;
    }

    public void setZestawSprzetowList(List<ZestawSprzetow> zestawSprzetowList) {
        this.zestawSprzetowList = zestawSprzetowList;
    }
}
