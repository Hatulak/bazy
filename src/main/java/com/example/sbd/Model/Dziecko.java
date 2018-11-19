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
    @JoinColumn(name = "RODZIC_ID")
    private Rodzic rodzic1;
    @ManyToOne
    @JoinColumn(name = "RODZIC_ID")
    private Rodzic rodzic2;
    @ManyToOne
    @JoinColumn(name = "GRUPA_ID")
    private Grupa grupa;
    @OneToMany(mappedBy = "CZESNE_ID", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Czesne> czesneList;


}
