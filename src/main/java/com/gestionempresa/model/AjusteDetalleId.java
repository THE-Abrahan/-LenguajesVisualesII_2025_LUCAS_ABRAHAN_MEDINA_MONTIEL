package com.gestionempresa.model;

import java.io.Serializable;

public class AjusteDetalleId implements Serializable {
    private Integer ajusId;
    private String prodId;
    private Integer ajustipId;

    public AjusteDetalleId() {}

    public AjusteDetalleId(Integer ajusId, String prodId, Integer ajustipId) {
        this.ajusId = ajusId;
        this.prodId = prodId;
        this.ajustipId = ajustipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AjusteDetalleId that = (AjusteDetalleId) o;
        return java.util.Objects.equals(ajusId, that.ajusId) && java.util.Objects.equals(prodId, that.prodId) && java.util.Objects.equals(ajustipId, that.ajustipId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(ajusId, prodId, ajustipId);
    }
}
