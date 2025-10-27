package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "arqueo")
@Getter
@Setter
public class Arqueo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arq_id")
    private Integer id;

    @Column(name = "arq_fecha")
    private LocalDate fecha;

    @Column(name = "arq_horadesde")
    private LocalTime horaDesde;

    @Column(name = "arq_horahasta")
    private LocalTime horaHasta;

    @Column(name = "arq_cheque")
    private Integer cheque;

    @Column(name = "arq_totalefectivo")
    private Integer totalEfectivo;

    @Column(name = "arq_estado", length = 1)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Caja_caj_id")
    private Caja caja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_usu_id")
    private Usuario usuario;

    @Column(name = "arq_montoinicio")
    private Integer montoInicio;
}
