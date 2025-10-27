package com.gestionempresa.model;

import java.io.Serializable;

public class VentaDetalleId implements Serializable {
    private String stockProductoProdId;
    private Integer ventaVenId;

    public VentaDetalleId() {}

    public VentaDetalleId(String stockProductoProdId, Integer ventaVenId) {
        this.stockProductoProdId = stockProductoProdId;
        this.ventaVenId = ventaVenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VentaDetalleId that = (VentaDetalleId) o;
        return java.util.Objects.equals(stockProductoProdId, that.stockProductoProdId) && java.util.Objects.equals(ventaVenId, that.ventaVenId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(stockProductoProdId, ventaVenId);
    }
}
