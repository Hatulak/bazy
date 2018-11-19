package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "SALASPORTOWA")
public class SalaSportowa {
    @Id
    @GeneratedValue
    @Column(name = "SALASPORTOWA_ID", nullable = false)
    private Long id;
    private Integer wielkosc;
    private Boolean czyTrybuna;
    @OneToOne
    @JoinColumn(name = "SZKOLA_ID")
    private Szkola szkola;
    @OneToMany(mappedBy = "salaSportowa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ZestawSprzetow> zestawSprzetowList;

}
