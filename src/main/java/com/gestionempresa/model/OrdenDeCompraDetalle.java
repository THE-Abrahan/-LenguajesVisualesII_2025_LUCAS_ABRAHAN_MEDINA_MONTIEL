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


@Entity
@Table(name = "orden_de_compra_detalle")
@Getter
@Setter
public class OrdenDeCompraDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orddetitem") // Se renombra el campo para que coincida con la columna de la BD
    private Integer orddetitem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Orden_de_compra_ordcomp_id")
    private OrdenDeCompra ordenDeCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Producto_prod_id")
    private Producto producto;

    @Column(name = "ordcompdet_cantidad", nullable = false, precision = 5, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "ordcompdet_preciounitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioUnitario;
}
