package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "RZUTNIK")
public class Rzutnik {
    @Id
    @GeneratedValue
    @Column(name = "RZUTNIK_ID", nullable = false)
    private Long id;
    private String model;
    private String jakoscObrazu;
    private Date dataZakupu;
    private Date dataWygasnieciaGwarancji;
}
