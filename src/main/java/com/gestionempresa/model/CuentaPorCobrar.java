package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cuenta_a_cobrar")
@Getter
@Setter
public class CuentaPorCobrar {

    @Embeddable
    @Getter
    @Setter
    public static class Id implements Serializable {
        @Column(name = "Venta_ven_id")
        private Integer venId;
        @Column(name = "cuentcob_cuotanumero") // Nombre de columna correcto
        private Integer cuotaNumero;

        public Id() {
        }

        public Id(Integer venId, Integer cuotaNumero) {
            this.venId = venId;
            this.cuotaNumero = cuotaNumero;
        }
    }

    @EmbeddedId
    private Id id;

    @Column(name = "cuentcob_fechavencimiento")
    private java.time.LocalDate fechaVencimiento;

    @Column(name = "cuentcob_monto", precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "cuentcob_estado")
    private String estado;

    public Integer getVenId() { return id != null ? id.venId : null; }
    public Integer getCuotaNumero() { return id != null ? id.cuotaNumero : null; }

}
