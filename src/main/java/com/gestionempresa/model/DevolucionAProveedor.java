package com.gestionempresa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "devolucion_a_proveedor")
public class DevolucionAProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "devprov_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "proveedor_prov_id")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "usuario_usu_id")
    private Usuario usuario;

    @jakarta.persistence.Column(name = "devprov_fecha")
    private LocalDate fecha;

    @jakarta.persistence.Column(name = "dev_prov_estado")
    private String estado;
}