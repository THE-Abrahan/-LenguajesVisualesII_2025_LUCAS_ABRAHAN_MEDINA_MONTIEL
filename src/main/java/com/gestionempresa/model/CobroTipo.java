package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cobro_tipo")
@Getter
@Setter
public class CobroTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cobtip_id")
    private Integer id;

    @Column(name = "cobtip_nombre", length = 20)
    private String nombre;

    @Column(name = "cobtip_descripcion", length = 45)
    private String descripcion;
}
