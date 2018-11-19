package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "CZESNE")
public class Czesne {
    @Id
    @GeneratedValue
    @Column(name = "CZESNE_ID", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "DZIECKO_ID")
    private Dziecko dziecko;
    private Date dataOplaty;
}
