package com.example.sbd.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ZESTAWSPRZETOW")
public class ZestawSprzetow {
    @Id
    @GeneratedValue
    @Column(name = "ZESTAWSPRZETOW_ID", nullable = false)
    private Long id;
    @OneToMany(mappedBy = "zestawSprzetow", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Sprzet> sprzetList;
    @ManyToOne
    @JoinColumn(name = "SALASPORTOWA_ID")
    private SalaSportowa salaSportowa;

}
