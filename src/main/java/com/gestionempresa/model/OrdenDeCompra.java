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
@Table(name = "orden_de_compra")
@Getter
@Setter
public class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordcomp_id")
    private Integer id;

    @Column(name = "ordcomp_fecha")
    private LocalDate fecha;

    @Column(name = "ordcomp_hora")
    private LocalTime hora;

    @Column(name = "ordcomp_estado", length = 3)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedprov_id", referencedColumnName = "pedprov_id")
    private PedidoProveedor pedidoProveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_usu_id")
    private Usuario usuario;
}
