package model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "KOMPUTER")
public class Komputer {
    @Id
    @GeneratedValue
    @Column(name = "KOMPUTER_ID", nullable = false)
    private Long id;
    private Date dataZakupu;
    private Date dataWygasnieciaGwarancji;
    @ManyToOne
    @JoinColumn(name = "SALA_ID")
    private Sala sala;
    private String specyfikacja;

    public Komputer() {
    }

    public Komputer(Date dataZakupu, Date dataWygasnieciaGwarancji, Sala sala, String specyfikacja) {
        this.dataZakupu = dataZakupu;
        this.dataWygasnieciaGwarancji = dataWygasnieciaGwarancji;
        this.sala = sala;
        this.specyfikacja = specyfikacja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataZakupu() {
        return dataZakupu;
    }

    public void setDataZakupu(Date dataZakupu) {
        this.dataZakupu = dataZakupu;
    }

    public Date getDataWygasnieciaGwarancji() {
        return dataWygasnieciaGwarancji;
    }

    public void setDataWygasnieciaGwarancji(Date dataWygasnieciaGwarancji) {
        this.dataWygasnieciaGwarancji = dataWygasnieciaGwarancji;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getSpecyfikacja() {
        return specyfikacja;
    }

    public void setSpecyfikacja(String specyfikacja) {
        this.specyfikacja = specyfikacja;
    }
}
