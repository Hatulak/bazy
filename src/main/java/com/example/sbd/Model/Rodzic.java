package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "RODZIC")
public class Rodzic {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private String imie;
    @OneToOne
    @JoinColumn(name = "MIASTO_ID")
    private Miasto miasto;
    private String ulica;
    private Integer nrMieszkania;
    private Integer telefon;
}
