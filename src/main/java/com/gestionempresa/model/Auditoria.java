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

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "auditoria")
@Getter
@Setter
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aud_cod")
    private Integer id;

    @Column(name = "aud_fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "aud_hora", nullable = false)
    private LocalTime hora;

    @Column(name = "aud_tabla", length = 45)
    private String tabla;

    @Column(name = "aud_operacion", length = 10)
    private String operacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_usu_id")
    private Usuario usuario;
}
