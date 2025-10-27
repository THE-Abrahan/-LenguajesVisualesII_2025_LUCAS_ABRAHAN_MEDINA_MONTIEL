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
@Table(name = "presupuesto_detalle")
@IdClass(PresupuestoDetalleId.class)
@Getter
@Setter
public class PresupuestoDetalle {

    @Id
    @Column(name = "pres_id")
    private Integer presId;

    @Id
    @Column(name = "prod_id")
    private String prodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pres_id", insertable = false, updatable = false)
    private Presupuesto presupuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id", insertable = false, updatable = false)
    private Producto producto;

    @Column(name = "presdet_cantidad", precision = 5, scale = 2)
    private BigDecimal presdetCantidad;

    @Column(name = "presdet_preciounitario", precision = 12, scale = 2)
    private BigDecimal presdetPrecioUnitario;
}
