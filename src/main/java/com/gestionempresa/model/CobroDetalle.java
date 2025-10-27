package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "cobro_detalle")
@IdClass(CobroDetalleId.class)
@Getter
@Setter
public class CobroDetalle {

    @Id
    @Column(name = "Cobro_cob_id")
    private Integer cobId;

    @Id
    @Column(name = "Cuenta_a_cobrar_cuentcob_cuotanumero")
    private Integer cuotaNumero;

    @Id
    @Column(name = "Cuenta_a_cobrar_Venta_ven_id")
    private Integer venId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cobro_cob_id", insertable = false, updatable = false)
    private Cobro cobro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "Cuenta_a_cobrar_Venta_ven_id", referencedColumnName = "venta_ven_id", insertable = false, updatable = false),
        @JoinColumn(name = "Cuenta_a_cobrar_cuentcob_cuotanumero", referencedColumnName = "cuentcob_cuotanumero", insertable = false, updatable = false)
    })
    private CuentaPorCobrar cuentaPorCobrar;
}
