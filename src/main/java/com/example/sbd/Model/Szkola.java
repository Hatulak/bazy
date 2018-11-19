package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "SZKOLA")
public class Szkola {
    @Id
    @GeneratedValue
    @Column(name = "SZKOLA_ID", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "MIASTO_ID")
    private Miasto miasto;
    private String nazwa;
    private String patron;
    private String ulica;
    private Integer nrBudynku;
    @OneToMany(mappedBy = "szkola", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Sala> salaList;

}
