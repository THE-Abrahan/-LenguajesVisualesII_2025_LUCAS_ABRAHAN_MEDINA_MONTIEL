package com.gestionempresa.model;

import java.io.Serializable;
import java.util.Objects;

public class DevolucionAProveedorDetalleId implements Serializable {
    // Los nombres deben coincidir con los campos @Id en la entidad
    private Integer devolucionAProveedorId;
    private String productoId;

    public DevolucionAProveedorDetalleId() {
    }

    public DevolucionAProveedorDetalleId(Integer devolucionAProveedorId, String productoId) {
        this.devolucionAProveedorId = devolucionAProveedorId;
        this.productoId = productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevolucionAProveedorDetalleId that = (DevolucionAProveedorDetalleId) o;
        return Objects.equals(devolucionAProveedorId, that.devolucionAProveedorId) &&
               Objects.equals(productoId, that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devolucionAProveedorId, productoId);
    }
}
