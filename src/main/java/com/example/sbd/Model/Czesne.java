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
    @Column(name = "ID", nullable = false)
    private Long id;
    private Long idDziecka;
    private Date dataOplaty;
}
