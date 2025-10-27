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
@Table(name = "devo_prov_deta")
@IdClass(DevolucionAProveedorDetalleId.class)
@Getter
@Setter
public class DevolucionAProveedorDetalle {

    @Id
    @Column(name = "Devolucion_a_proveedor_devprov_id")
    private Integer devolucionAProveedorId;

    @Id
    @Column(name = "stock_producto_prod_id")
    private String productoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Stock_Producto_prod_id", insertable = false, updatable = false)
    private Producto producto;

    @Column(name = "devprov_cantidad")
    private BigDecimal devprovCantidad;

    @Column(name = "devprov_precio")
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Devolucion_a_proveedor_devprov_id", referencedColumnName = "devprov_id", insertable = false, updatable = false)
    private DevolucionAProveedor devolucionAProveedor;
}