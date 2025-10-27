package com.gestionempresa.repository;

import com.gestionempresa.model.PresupuestoDetalle;
import com.gestionempresa.model.PresupuestoDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresupuestoDetalleRepository extends JpaRepository<PresupuestoDetalle, PresupuestoDetalleId> {
}
