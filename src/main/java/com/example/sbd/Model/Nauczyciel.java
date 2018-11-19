package com.example.sbd.Model;

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
    private Long idMiasto;
    private String ulica;
    private Integer nrMieszkania;

}
