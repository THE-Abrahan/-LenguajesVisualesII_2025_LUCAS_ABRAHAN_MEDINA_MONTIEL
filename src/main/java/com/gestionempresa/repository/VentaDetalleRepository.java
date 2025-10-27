package com.gestionempresa.repository;

import com.gestionempresa.model.VentaDetalle;
import com.gestionempresa.model.VentaDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, VentaDetalleId> {
}

