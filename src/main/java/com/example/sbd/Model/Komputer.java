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
    @Column(name = "ID", nullable = false)
    private Long id;
    private Date dataZakupu;
    private Long idSali;
    private String specyfikacja;
    private Date dataWygasnieciaGwarancji;

}
