package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ZESTAWSPRZETOW")
public class ZestawSprzetow {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    private Long idSprzetu;
    private Long idSali;

}
