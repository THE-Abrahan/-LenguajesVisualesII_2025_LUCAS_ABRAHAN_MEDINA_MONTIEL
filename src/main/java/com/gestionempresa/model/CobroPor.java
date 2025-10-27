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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cobro_por")
@Getter
@Setter
public class CobroPor {

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "cobro_detalle_cobro_cob_id")
        private Integer cobroDetalleCobroCobId;

        @Column(name = "cobro_detalle_cuenta_a_cobrar_venta_ven_id")
        private Integer cobroDetalleCuentaACobrarVentaVenId;

        @Column(name = "cobro_detalle_cuenta_a_cobrar_cuentcob_cuotanumero")
        private Integer cobroDetalleCuentaACobrarCuentcobCuotanumero;

        @Column(name = "cobro_tipo_cobtip_id")
        private Integer cobroTipoCobtipId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(cobroDetalleCobroCobId, id.cobroDetalleCobroCobId) && Objects.equals(cobroDetalleCuentaACobrarVentaVenId, id.cobroDetalleCuentaACobrarVentaVenId) && Objects.equals(cobroDetalleCuentaACobrarCuentcobCuotanumero, id.cobroDetalleCuentaACobrarCuentcobCuotanumero) && Objects.equals(cobroTipoCobtipId, id.cobroTipoCobtipId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cobroDetalleCobroCobId, cobroDetalleCuentaACobrarVentaVenId, cobroDetalleCuentaACobrarCuentcobCuotanumero, cobroTipoCobtipId);
        }
    }

    @EmbeddedId
    private Id id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cobro_tipo_cobtip_id", insertable = false, updatable = false)
    private CobroTipo cobroTipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "cobro_detalle_cobro_cob_id", referencedColumnName = "Cobro_cob_id", insertable = false, updatable = false),
        @JoinColumn(name = "cobro_detalle_cuenta_a_cobrar_venta_ven_id", referencedColumnName = "Cuenta_a_cobrar_Venta_ven_id", insertable = false, updatable = false),
        @JoinColumn(name = "cobro_detalle_cuenta_a_cobrar_cuentcob_cuotanumero", referencedColumnName = "Cuenta_a_cobrar_cuentcob_cuotanumero", insertable = false, updatable = false)
    })
    private CobroDetalle cobroDetalle;

    @Column(name = "monto")
    private BigDecimal monto;
}