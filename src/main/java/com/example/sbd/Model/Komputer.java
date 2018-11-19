package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "KOMPUTER")
public class Komputer {
    @Id
    @GeneratedValue
    @Column(name = "KOMPUTER_ID", nullable = false)
    private Long id;
    private Date dataZakupu;
    private Date dataWygasnieciaGwarancji;
    @ManyToOne
    @JoinColumn(name = "SALA_ID")
    private Sala sala;
    private String specyfikacja;

}
