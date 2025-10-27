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
@Table(name = "ajustes_tipo")
@Getter
@Setter
public class AjusteTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ajustip_id")
    private Integer id;

    @Column(name = "ajustip_tipo", length = 3)
    private String tipo;

    @Column(name = "ajustip_motivo", length = 45)
    private String motivo;
}
