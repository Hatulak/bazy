package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SALA")
public class Sala {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private Integer liczbaKrzesel;
    private Integer liczbaLawek;
    private Long idPlacowki;
    private Long idRzutnika;

}
