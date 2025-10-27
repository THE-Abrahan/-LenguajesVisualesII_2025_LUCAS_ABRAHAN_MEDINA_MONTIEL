package com.gestionempresa.repository;

import com.gestionempresa.model.DevolucionAProveedorDetalle;
import com.gestionempresa.model.DevolucionAProveedorDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevolucionAProveedorDetalleRepository extends JpaRepository<DevolucionAProveedorDetalle, DevolucionAProveedorDetalleId> {
}