package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "RODZIC")
public class Rodzic {
    @Id
    @GeneratedValue
    @Column(name = "RODZIC_ID", nullable = false)
    private Long id;
    private String imie;
    @OneToOne
    @JoinColumn(name = "MIASTO_ID")
    private Miasto miasto;
    private String ulica;
    private Integer nrMieszkania;
    private Integer telefon;
    @OneToMany(mappedBy = "DZIECKO_ID", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Dziecko> dzieckoList;
}
