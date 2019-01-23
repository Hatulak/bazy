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

    @OneToMany(mappedBy = "zestawSprzetow", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.DETACH})
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

    public void addSprzetToList(Sprzet sprzet) {
        sprzetList.add(sprzet);
    }

    public void removeSprzetFromList(Sprzet sprzet) {
        int toRemove = -1;
        for (int i = 0; i < sprzetList.size(); i++) {
            if (sprzetList.get(i).getId().equals(sprzet.getId())) {
                toRemove = i;
                break;
            }
        }
        sprzetList.remove(toRemove);
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
