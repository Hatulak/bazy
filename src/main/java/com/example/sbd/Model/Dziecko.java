package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DZIECKO")
public class Dziecko {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private Long idRodzic1;
    private Long idRodzic2;
    private Long idGrupy;
    private Integer wiek;
    private String imie;

}
