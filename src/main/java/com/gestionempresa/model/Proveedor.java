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

@Entity
@Table(name = "proveedor")
@Getter
@Setter
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prov_id")
    private Integer id;

    @Column(name = "prov_nombre", length = 45)
    private String nombre;

    @Column(name = "prov_direccion", length = 45)
    private String direccion;

    @Column(name = "prov_telefono", length = 15)
    private String telefono;

    @Column(name = "prov_pag_web", length = 35)
    private String pagWeb;

    @Column(name = "prov_email", length = 35)
    private String email;

    @Column(name = "prov_estado")
    private Integer estado;

    @Column(name = "prov_observacion", length = 100)
    private String observacion;

    @Column(name = "prov_ruc", length = 10)
    private String ruc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ciudad_ciu_id")
    private Ciudad ciudad;
}
