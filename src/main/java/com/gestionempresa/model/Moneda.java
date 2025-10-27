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
@Table(name = "moneda")
@Getter
@Setter
public class Moneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mon_id")
    private Integer id;

    @Column(name = "mon_descripcion", nullable = false, length = 20)
    private String descripcion;

    @Column(name = "mon_sigla", nullable = false, length = 10)
    private String sigla;
}
