package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MIASTO")
public class Miasto {
    @Id
    @GeneratedValue
    @Column(name = "MIASTO_ID", nullable = false)
    private Long id;
    private String nazwa;
    private String gmina;
    private String powiat;
    private String wojewodztwo;
}
