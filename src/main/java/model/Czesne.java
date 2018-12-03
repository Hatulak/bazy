package model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CZESNE")
public class Czesne {
    @Id
    @GeneratedValue
    @Column(name = "CZESNE_ID", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "DZIECKO_ID")
    private Dziecko dziecko;
    private Date dataOplaty;
    private Double kwota;

    public Czesne() {
    }

    public Double getKwota() {
        return kwota;
    }

    public void setKwota(Double kwota) {
        this.kwota = kwota;
    }

    public Czesne(Dziecko dziecko, Date dataOplaty) {
        this.dziecko = dziecko;
        this.dataOplaty = dataOplaty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dziecko getDziecko() {
        return dziecko;
    }

    public void setDziecko(Dziecko dziecko) {
        this.dziecko = dziecko;
    }

    public Date getDataOplaty() {
        return dataOplaty;
    }

    public void setDataOplaty(Date dataOplaty) {
        this.dataOplaty = dataOplaty;
    }

    @Override
    public String toString() {
        return "Czesne{" +
                "id=" + id +
                ", dziecko=" + dziecko +
                ", dataOplaty=" + dataOplaty +
                ", kwota=" + kwota +
                '}';
    }
}
