package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "SZKOLA_ID")
    private Szkola szkola;
    @OneToOne
    @JoinColumn(name = "RZUTNIK_ID")
    private Rzutnik rzutnik;
    @OneToMany(mappedBy = "KOMPUTER_ID", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Komputer> komputerList;


}
