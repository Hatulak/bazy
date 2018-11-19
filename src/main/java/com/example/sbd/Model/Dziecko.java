package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "KOGORODZIC_ID")
    private KogoRodzic kogoRodzic;
    @ManyToOne
    @JoinColumn(name = "GRUPA_ID")
    private Grupa grupa;
    @OneToMany(mappedBy = "dziecko", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Czesne> czesneList;


}
