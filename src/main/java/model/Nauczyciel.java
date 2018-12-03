package model;

import javax.persistence.*;

@Entity
@Table(name = "NAUCZYCIEL")
public class Nauczyciel {
    @Id
    @GeneratedValue
    @Column(name = "NAUCZYCIEL_ID", nullable = false)
    private Long id;
    private String imie;
    private String nazwisko;
    private String email;
    private Integer telefon;
    private String stopien;
    @OneToOne
    @JoinColumn(name = "MIASTO_ID")
    private Miasto miasto;
    private String adres;
    @ManyToOne
    @JoinColumn(name = "SZKOLA_ID")
    private Szkola szkola;

    public Nauczyciel() {
    }

    public Nauczyciel(String imie, String nazwisko, String email, Integer telefon, String stopien, Miasto miasto, String adres, Szkola szkola) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.telefon = telefon;
        this.stopien = stopien;
        this.miasto = miasto;
        this.adres = adres;
        this.szkola = szkola;
    }

    public Szkola getSzkola() {
        return szkola;
    }

    public void setSzkola(Szkola szkola) {
        this.szkola = szkola;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
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

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public String getStopien() {
        return stopien;
    }

    public void setStopien(String stopien) {
        this.stopien = stopien;
    }

    public Miasto getMiasto() {
        return miasto;
    }

    public void setMiasto(Miasto miasto) {
        this.miasto = miasto;
    }


}
