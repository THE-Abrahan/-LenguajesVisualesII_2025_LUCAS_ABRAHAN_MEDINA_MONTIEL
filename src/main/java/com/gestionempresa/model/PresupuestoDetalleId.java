package com.gestionempresa.model;

import java.io.Serializable;

public class PresupuestoDetalleId implements Serializable {
    private Integer presId;
    private String prodId;

    public PresupuestoDetalleId() {}

    public PresupuestoDetalleId(Integer presId, String prodId) {
        this.presId = presId;
        this.prodId = prodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresupuestoDetalleId that = (PresupuestoDetalleId) o;
        return java.util.Objects.equals(presId, that.presId) && java.util.Objects.equals(prodId, that.prodId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(presId, prodId);
    }
}
