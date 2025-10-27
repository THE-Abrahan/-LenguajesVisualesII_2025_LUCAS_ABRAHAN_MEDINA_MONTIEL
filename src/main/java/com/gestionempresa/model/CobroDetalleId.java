package com.gestionempresa.model;

import java.io.Serializable;

public class CobroDetalleId implements Serializable {
    private Integer cobId;
    // Debe coincidir con el nombre del campo @Id en la entidad: cuotaNumero
    private Integer cuotaNumero;
    private Integer venId;

    public CobroDetalleId() {}

    public CobroDetalleId(Integer cobId, Integer cuotaNumero, Integer venId) {
        this.cobId = cobId;
        this.cuotaNumero = cuotaNumero;
        this.venId = venId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CobroDetalleId that = (CobroDetalleId) o;
        return java.util.Objects.equals(cobId, that.cobId) && java.util.Objects.equals(cuotaNumero, that.cuotaNumero) && java.util.Objects.equals(venId, that.venId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cobId, cuotaNumero, venId);
    }
}
