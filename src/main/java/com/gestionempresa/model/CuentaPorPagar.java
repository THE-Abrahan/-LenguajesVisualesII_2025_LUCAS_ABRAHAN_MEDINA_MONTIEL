package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cuenta_a_pagar")
@Getter
@Setter
public class CuentaPorPagar {

    @Embeddable
    @Getter
    @Setter
    public static class Id implements Serializable {
        @Column(name = "cuenpag_numerocuota")
        private Integer cuenpagNumerocuota;

        @Column(name = "compra_numerofactura")
        private String compraNumerofactura;

        public Id() {
        }

        public Id(Integer cuenpagNumerocuota, String compraNumerofactura) { // Constructor
            this.cuenpagNumerocuota = cuenpagNumerocuota;
            this.compraNumerofactura = compraNumerofactura;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(cuenpagNumerocuota, id.cuenpagNumerocuota) && Objects.equals(compraNumerofactura, id.compraNumerofactura);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cuenpagNumerocuota, compraNumerofactura);
        }
    }

    @EmbeddedId
    private Id id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_numerofactura", referencedColumnName = "comp_numerofactura", insertable = false, updatable = false)
    private Compra compra;

    @Column(name = "cuenpag_monto", precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "cuenpag_estado")
    private String estado;
}
