package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SALA")
public class Sala {
    @Id
    @GeneratedValue
    @Column(name = "SALA_ID", nullable = false)
    private Long id;
    private Integer liczbaKrzesel;
    private Integer liczbaLawek;
    private Long idPlacowki;
    @OneToOne
    @JoinColumn(name = "RZUTNIK_ID")
    private Long idRzutnika;

}
