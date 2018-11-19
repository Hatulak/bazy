package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SZAFKA")
public class Szafka {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private Integer numer;
    private String haslo;
    private Integer pojemnosc;
    private Long idDziecka;
}
