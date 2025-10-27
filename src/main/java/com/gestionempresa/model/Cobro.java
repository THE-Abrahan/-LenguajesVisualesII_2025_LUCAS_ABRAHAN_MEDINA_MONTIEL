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
@Table(name = "cobro")
@Getter
@Setter
public class Cobro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cob_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Arqueo_arq_id", nullable = false)
    private Arqueo arqueo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Usuario_usu_id")
    private Usuario usuario;

    @Column(name = "cob_fecha")
    private LocalDate fecha;

    @Column(name = "cob_hora")
    private LocalTime hora;

    @Column(name = "cob_estado", length = 3)
    private String estado;
}
