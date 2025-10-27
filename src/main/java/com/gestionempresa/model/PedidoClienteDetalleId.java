package com.gestionempresa.model;

import java.io.Serializable;

public class PedidoClienteDetalleId implements Serializable {
    private Integer pedcliId;
    // Debe coincidir con el nombre del campo en la entidad: stockProductoProdId
    private String stockProductoProdId;

    public PedidoClienteDetalleId() {}

    public PedidoClienteDetalleId(Integer pedcliId, String stockProductoProdId) {
        this.pedcliId = pedcliId;
        this.stockProductoProdId = stockProductoProdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoClienteDetalleId that = (PedidoClienteDetalleId) o;
        return java.util.Objects.equals(pedcliId, that.pedcliId) && java.util.Objects.equals(stockProductoProdId, that.stockProductoProdId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(pedcliId, stockProductoProdId);
    }
}
