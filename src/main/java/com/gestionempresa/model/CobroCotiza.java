package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "cobro_cotiza")
@Getter
@Setter
public class CobroCotiza {

    @Embeddable
    @Getter
    @Setter
    public static class Id implements Serializable {
        @Column(name = "cob_id")
        private Integer cobId;
        @Column(name = "ven_id")
        private Integer venId;
        @Column(name = "cuentcob_cuotanumero")
        private Integer cuotaNumero;
        @Column(name = "cot_codigo")
        private Integer cotCodigo;
    }

    @EmbeddedId
    private Id id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cob_id", insertable = false, updatable = false)
    private Cobro cobro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ven_id", referencedColumnName = "venta_ven_id", insertable = false, updatable = false), // 'ven_id' en esta tabla se une con 'venta_ven_id' en cuenta_a_cobrar
            @JoinColumn(name = "cuentcob_cuotanumero", referencedColumnName = "cuentcob_cuotanumero", insertable = false, updatable = false) // 'cuentcob_cuotanumero' se une con 'cuentcob_cuotanumero'
    })
    private CuentaPorCobrar cuentaPorCobrar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cot_codigo", insertable = false, updatable = false)
    private Cotizacion cotizacion;
}
