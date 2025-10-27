package com.gestionempresa.model;

import java.io.Serializable;
import java.util.Objects;

public class OrdenDePagoDetalleId implements Serializable { 
    private Integer ordenDePagoOrdpagNumero;
    private Integer cuenpagNumerocuota;
    private String compNumerofactura;

    public OrdenDePagoDetalleId() {}

    public OrdenDePagoDetalleId(Integer ordenDePagoOrdpagNumero, Integer cuenpagNumerocuota, String compNumerofactura) {
        this.ordenDePagoOrdpagNumero = ordenDePagoOrdpagNumero;
        this.cuenpagNumerocuota = cuenpagNumerocuota;
        this.compNumerofactura = compNumerofactura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenDePagoDetalleId that = (OrdenDePagoDetalleId) o;
        return Objects.equals(ordenDePagoOrdpagNumero, that.ordenDePagoOrdpagNumero) &&
               Objects.equals(cuenpagNumerocuota, that.cuenpagNumerocuota) &&
               Objects.equals(compNumerofactura, that.compNumerofactura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordenDePagoOrdpagNumero, cuenpagNumerocuota, compNumerofactura);
    }
}
