package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SZKOLA")
public class Szkola {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private Long idMiasto;
    private String nazwa;
    private String patron;
    private String ulica;
    private Integer nrBudynku;

}
