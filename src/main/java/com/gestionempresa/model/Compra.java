package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "compra")
@Getter
@Setter
public class Compra {

    @Id
    @Column(name = "comp_numerofactura")
    private String numeroFactura;

    @Column(name = "comp_fecha")
    private LocalDate fecha;

    @Column(name = "comp_horacompra")
    private LocalTime horaCompra;

    @Column(name = "comp_estado", length = 3)
    private String estado;

    @Column(name = "comp_fecharegistro")
    private LocalDate fechaRegistro;

    @Column(name = "comp_tipo", length = 3)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Proveedor_prov_id")
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_usu_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Orden_de_compra_ordcomp_id")
    private OrdenDeCompra ordenDeCompra;
}
