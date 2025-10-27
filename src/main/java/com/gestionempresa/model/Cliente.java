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
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Integer id;

    @Column(name = "cli_nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "cli_apellido", nullable = false, length = 25)
    private String apellido;

    @Column(name = "cli_sexo", nullable = false, length = 1)
    private String sexo;

    @Column(name = "cli_direccion", length = 45)
    private String direccion;

    @Column(name = "cli_email", length = 35)
    private String email;

    @Column(name = "cli_telefono", length = 15)
    private String telefono;

    @Column(name = "cli_ruc", length = 10)
    private String ruc;

    @Column(name = "cli_ci", length = 10)
    private String ci;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciu_id")
    private Ciudad ciudad;
}