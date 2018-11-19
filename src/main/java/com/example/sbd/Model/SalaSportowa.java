package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SALASPORTOWA")
public class SalaSportowa {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private Integer wielkosc;
    private Boolean czyTrybuna;
    private Long idPlacowki;

}
