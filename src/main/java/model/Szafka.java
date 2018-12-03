package model;


import javax.persistence.*;

@Entity
@Table(name = "SZAFKA")
public class Szafka {
    @Id
    @GeneratedValue
    @Column(name = "SZAFKA_ID", nullable = false)
    private Long id;
    private Integer numer;
    private String haslo;
    private Integer pojemnosc;
    @OneToOne
    @JoinColumn(name = "DZIECKO_ID")
    private Dziecko dziecko;
    @ManyToOne
    @JoinColumn(name = "SZKOLA_ID")
    private Szkola szkola;

    public Szafka() {
    }

    public Szafka(Integer numer, String haslo, Integer pojemnosc, Dziecko dziecko, Szkola szkola) {
        this.numer = numer;
        this.haslo = haslo;
        this.pojemnosc = pojemnosc;
        this.dziecko = dziecko;
        this.szkola = szkola;
    }

    public Szkola getSzkola() {
        return szkola;
    }

    public void setSzkola(Szkola szkola) {
        this.szkola = szkola;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumer() {
        return numer;
    }

    public void setNumer(Integer numer) {
        this.numer = numer;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Integer getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(Integer pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public Dziecko getDziecko() {
        return dziecko;
    }

    public void setDziecko(Dziecko dziecko) {
        this.dziecko = dziecko;
    }
}
