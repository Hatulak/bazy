package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SPRZET")
public class Sprzet {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private String nazwa;
    private String dyscyplina;
    private Integer ilosc;
    private Long idZestawu;


}
