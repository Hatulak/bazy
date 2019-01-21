package model;


import javax.persistence.*;


@Entity
@Table(name = "SPRZET")
public class Sprzet {
    @Id
    @GeneratedValue
    @Column(name = "SPRZET_ID", nullable = false)
    private Long id;
    private String nazwa;
    private Integer ilosc;
    @ManyToOne
    @JoinColumn(name = "ZESTAWSPRZETOW_ID")
    private ZestawSprzetow zestawSprzetow;

    public Sprzet(String nazwa, Integer ilosc, ZestawSprzetow zestawSprzetow) {
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.zestawSprzetow = zestawSprzetow;
    }

    public Sprzet() {
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }


    public ZestawSprzetow getZestawSprzetow() {
        return zestawSprzetow;
    }

    public void setZestawSprzetow(ZestawSprzetow zestawSprzetow) {
        this.zestawSprzetow = zestawSprzetow;
    }
}

