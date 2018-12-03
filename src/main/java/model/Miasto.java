package model;


import javax.persistence.*;


@Entity
@Table(name = "MIASTO")
public class Miasto {
    @Id
    @GeneratedValue
    @Column(name = "MIASTO_ID", nullable = false)
    private Long id;
    private String nazwa;
    private String gmina;
    private String powiat;
    private String wojewodztwo;

    public Miasto() {
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

    public String getGmina() {
        return gmina;
    }

    public void setGmina(String gmina) {
        this.gmina = gmina;
    }

    public String getPowiat() {
        return powiat;
    }

    public void setPowiat(String powiat) {
        this.powiat = powiat;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public Miasto(String nazwa, String gmina, String powiat, String wojewodztwo) {
        this.nazwa = nazwa;
        this.gmina = gmina;
        this.powiat = powiat;
        this.wojewodztwo = wojewodztwo;
    }
}
