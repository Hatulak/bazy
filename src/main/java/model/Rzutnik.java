package model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "RZUTNIK")
public class Rzutnik {
    @Id
    @GeneratedValue
    @Column(name = "RZUTNIK_ID", nullable = false)
    private Long id;
    private String model;
    private String jakoscObrazu;
    private Date dataZakupu;
    private Date dataWygasnieciaGwarancji;

    public Rzutnik() {
    }

    public Rzutnik(String model, String jakoscObrazu, Date dataZakupu, Date dataWygasnieciaGwarancji) {
        this.model = model;
        this.jakoscObrazu = jakoscObrazu;
        this.dataZakupu = dataZakupu;
        this.dataWygasnieciaGwarancji = dataWygasnieciaGwarancji;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getJakoscObrazu() {
        return jakoscObrazu;
    }

    public void setJakoscObrazu(String jakoscObrazu) {
        this.jakoscObrazu = jakoscObrazu;
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
}
