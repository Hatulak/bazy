package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ZESTAWSPRZETOW")
public class ZestawSprzetow {
    @Id
    @GeneratedValue
    @Column(name = "ZESTAWSPRZETOW_ID", nullable = false)
    private Long id;
    private String dyscyplina;

    @OneToMany(mappedBy = "zestawSprzetow", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sprzet> sprzetList;

    @ManyToOne
    @JoinColumn(name = "SALASPORTOWA_ID")
    private SalaSportowa salaSportowa;

    public ZestawSprzetow() {
    }

    public ZestawSprzetow(String dyscyplina, List<Sprzet> sprzetList, SalaSportowa salaSportowa) {
        this.dyscyplina = dyscyplina;
        this.sprzetList = sprzetList;
        this.salaSportowa = salaSportowa;
    }

    public String getDyscyplina() {
        return dyscyplina;
    }

    public void setDyscyplina(String dyscyplina) {
        this.dyscyplina = dyscyplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Sprzet> getSprzetList() {
        return sprzetList;
    }

    public void setSprzetList(List<Sprzet> sprzetList) {
        this.sprzetList = sprzetList;
    }

    public SalaSportowa getSalaSportowa() {
        return salaSportowa;
    }

    public void setSalaSportowa(SalaSportowa salaSportowa) {
        this.salaSportowa = salaSportowa;
    }
}
