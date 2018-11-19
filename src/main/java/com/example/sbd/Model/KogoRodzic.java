package com.example.sbd.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "KOGORODZIC")
public class KogoRodzic {
    @Id
    @GeneratedValue
    @Column(name = "KOGORODZIC_ID", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "RODZIC_ID")
    private Rodzic rodzic;
    @OneToMany
    private List<Dziecko> dzieckoList;
}
