package com.gestionempresa.model;

import java.io.Serializable;
import java.util.Objects;

public class CompraDetalleId implements Serializable {
    private String compNumerofactura;
    private String stockProductoProdId;

    public CompraDetalleId() {
    }

    public CompraDetalleId(String compNumerofactura, String stockProductoProdId) {
        this.compNumerofactura = compNumerofactura;
        this.stockProductoProdId = stockProductoProdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraDetalleId that = (CompraDetalleId) o;
        return Objects.equals(compNumerofactura, that.compNumerofactura) && Objects.equals(stockProductoProdId, that.stockProductoProdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compNumerofactura, stockProductoProdId);
    }
}