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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "venta")
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ven_id")
    private Integer id;

    @Column(name = "ven_fechafacturacion")
    private LocalDate fechaFacturacion;

    @Column(name = "ven_hora")
    private LocalTime hora;

    @Column(name = "ven_estado", length = 3)
    private String estado;

    @Column(name = "ven_descuento", precision = 12, scale = 2)
    private BigDecimal descuento;

    @Column(name = "ven_tipo", length = 3)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cliente_cli_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_usu_id")
    private Usuario usuario;
}
