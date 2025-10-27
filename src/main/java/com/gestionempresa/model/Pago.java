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

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
@Getter
@Setter
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pag_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Orden_de_pago_ordpag_numero")
    private OrdenDePago ordenDePago;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Usuario_usu_id")
    private Usuario usuario;

    @Column(name = "pag_fecha")
    private LocalDate fecha;

    @Column(name = "pag_monto", precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "pag_estado", length = 1)
    private String estado;
}
