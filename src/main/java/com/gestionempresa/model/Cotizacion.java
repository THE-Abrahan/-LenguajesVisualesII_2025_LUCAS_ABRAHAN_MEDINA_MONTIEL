package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cotizacion")
@Getter
@Setter
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cot_codigo")
    private Integer id;

    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "Moneda_mon_codigo")
    private Moneda moneda;

    @Column(name = "cot_precompra", precision = 12, scale = 2)
    private BigDecimal precompra;

    @Column(name = "cot_preventa", precision = 12, scale = 2)
    private BigDecimal preventa;

    @Column(name = "cot_fecha")
    private LocalDate fecha;

    @Column(name = "cot_hora")
    private LocalTime hora;
}
