package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "venta_detalle")
@IdClass(VentaDetalleId.class)
@Getter
@Setter
public class VentaDetalle {

    @Id
    @Column(name = "Stock_Producto_prod_id")
    private String stockProductoProdId;

    @Id
    @Column(name = "Venta_ven_id")
    private Integer ventaVenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Venta_ven_id", insertable = false, updatable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Stock_Producto_prod_id", referencedColumnName = "prod_id", insertable = false, updatable = false)
    private Producto producto;

    @Column(name = "detventa_preciounitario", precision = 12, scale = 2)
    private BigDecimal detventaPrecioUnitario;

    @Column(name = "detventa_cantidad", precision = 5, scale = 2)
    private BigDecimal detventaCantidad;
}
