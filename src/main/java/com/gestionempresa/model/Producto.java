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

@Entity
@Table(name = "producto")
@Getter
@Setter
public class Producto {

    @Id
    @Column(name = "prod_id", length = 13)
    private String id;

    @Column(name = "prod_nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "prod_descripcion", length = 50)
    private String descripcion;

    @Column(name = "prod_preciocompra")
    private Double precioCompra;

    @Column(name = "prod_iva", length = 3)
    private String iva;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Marca_mar_id", nullable = false)
    private Marca marca;
}