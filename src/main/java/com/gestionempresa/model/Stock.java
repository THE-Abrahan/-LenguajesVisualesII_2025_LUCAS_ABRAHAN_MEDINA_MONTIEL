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

import java.math.BigDecimal;

@Entity
@Table(name = "stock")
@Getter
@Setter
public class Stock {

    @Id
    @Column(name = "Producto_prod_id", length = 13)
    private String productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Producto_prod_id", insertable = false, updatable = false)
    private Producto producto;

    @Column(name = "stock_cantidadmin", precision = 5, scale = 2)
    private BigDecimal cantidadMin;

    @Column(name = "stock_cantidadmax", precision = 5, scale = 2)
    private BigDecimal cantidadMax;

    @Column(name = "stock_cantidadactual", precision = 5, scale = 2)
    private BigDecimal cantidadActual;
}
