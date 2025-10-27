package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "pedido_proveedor_detalle")
@Getter
@Setter
public class PedidoProveedorDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se asegura que el ID sea autogenerado
    @Column(name = "peddet_item")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedprov_id", referencedColumnName = "pedprov_id")
    private PedidoProveedor pedidoProveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Producto_prod_id")
    private Producto producto;

    @Column(name = "pedprovdet_cantidad", precision = 5, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "pedprovdet_preciounitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;
}
