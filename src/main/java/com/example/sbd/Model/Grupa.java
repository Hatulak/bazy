package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "GRUPA")
public class Grupa {
    @Id
    @GeneratedValue
    @Column(name = "GRUPA_ID", nullable = false)
    private Long id;
    private Integer maxLiczbaDzieci;
    private Integer wiek;
    @OneToOne
    @JoinColumn(name = "NAUCZYCIEL_ID")
    private Nauczyciel nauczyciel;
    @OneToOne
    @JoinColumn(name = "SALA_ID")
    private Sala sala;
    @OneToMany(mappedBy = "DZIECKO_ID", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Dziecko> dzieckoList;

}
