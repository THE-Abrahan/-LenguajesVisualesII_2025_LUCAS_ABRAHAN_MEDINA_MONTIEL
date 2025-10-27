package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "orden_de_pago_detalle")
@IdClass(OrdenDePagoDetalleId.class)
@Getter
@Setter
public class OrdenDePagoDetalle {

    @Id
    @Column(name = "Orden_de_pago_ordpag_numero")
    private Integer ordenDePagoOrdpagNumero;

    @Id
    @Column(name = "cuenpag_numerocuota")
    private Integer cuenpagNumerocuota;

    @Id
    @Column(name = "comp_numerofactura")
    private String compNumerofactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Orden_de_pago_ordpag_numero", insertable = false, updatable = false)
    private OrdenDePago ordenDePago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "cuenpag_numerocuota", referencedColumnName = "cuenpag_numerocuota", insertable = false, updatable = false),
        @JoinColumn(name = "comp_numerofactura", referencedColumnName = "compra_numerofactura", insertable = false, updatable = false)
    })
    private CuentaPorPagar cuentaPorPagar;

    @Column(name = "ordpagdet_monto", precision = 12, scale = 2)
    private BigDecimal monto;
}
