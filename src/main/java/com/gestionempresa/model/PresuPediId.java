package com.gestionempresa.model;

import java.io.Serializable;
import java.util.Objects;

public class PresuPediId implements Serializable {
    private Integer presupuestoPresId;
    private Integer pedidoClientePedcliId;

    public PresuPediId() {}

    public PresuPediId(Integer presupuestoPresId, Integer pedidoClientePedcliId) {
        this.presupuestoPresId = presupuestoPresId;
        this.pedidoClientePedcliId = pedidoClientePedcliId;
    }

    public Integer getPresupuestoPresId() { return presupuestoPresId; }
    public void setPresupuestoPresId(Integer presupuestoPresId) { this.presupuestoPresId = presupuestoPresId; }
    public Integer getPedidoClientePedcliId() { return pedidoClientePedcliId; }
    public void setPedidoClientePedcliId(Integer pedidoClientePedcliId) { this.pedidoClientePedcliId = pedidoClientePedcliId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresuPediId that = (PresuPediId) o;
        return Objects.equals(presupuestoPresId, that.presupuestoPresId) &&
               Objects.equals(pedidoClientePedcliId, that.pedidoClientePedcliId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presupuestoPresId, pedidoClientePedcliId);
    }
}
