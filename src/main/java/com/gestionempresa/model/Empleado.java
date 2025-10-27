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
import java.math.BigDecimal;

@Entity
@Table(name = "empleado")
@Getter
@Setter
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer id;

    @Column(name = "emp_nombre", length = 20)
    private String nombre;

    @Column(name = "emp_apellido", length = 20)
    private String apellido;

    @Column(name = "emp_ci", length = 10)
    private String ci;

    @Column(name = "emp_sueldo", precision = 12, scale = 2)
    private BigDecimal sueldo;

    @Column(name = "emp_direccion", length = 45)
    private String direccion;

    @Column(name = "emp_telefono", length = 15)
    private String telefono;

    @Column(name = "emp_fechanacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "emp_observacion", length = 100)
    private String observacion;

    @Column(name = "emp_email", length = 35)
    private String email;

    @Column(name = "emp_sexo", length = 1)
    private String sexo;

    @Column(name = "emp_estado")
    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ciudad_ciu_id")
    private Ciudad ciudad;
}
