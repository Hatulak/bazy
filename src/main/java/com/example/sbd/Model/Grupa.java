package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GRUPA")
public class Grupa {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private Integer maxLiczbaDzieci;
    private Integer wiek;
    @OneToOne
    @JoinColumn(name = "NAUCZYCIEL_ID")
    private Nauczyciel nauczyciel;
    @OneToOne
    @JoinColumn(name = "SALA_ID")
    private Sala sala;

}
