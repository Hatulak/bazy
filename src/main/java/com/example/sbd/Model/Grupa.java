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
    private Long idWychowawcy;
    private Long idSala;

}
