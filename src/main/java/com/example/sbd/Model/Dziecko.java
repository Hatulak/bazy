package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DZIECKO")
public class Dziecko {
    @Id
    @GeneratedValue
    @Column(name = "DZIECKO_ID", nullable = false)
    private Long id;
    private String imie;
    private Integer wiek;
    private Long idRodzic1;
    private Long idRodzic2;
    private Long idGrupy;


}
