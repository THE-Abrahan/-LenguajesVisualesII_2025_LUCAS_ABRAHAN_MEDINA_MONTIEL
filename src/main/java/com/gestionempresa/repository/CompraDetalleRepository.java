package com.gestionempresa.repository;

import com.gestionempresa.model.CompraDetalle;
import com.gestionempresa.model.CompraDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, CompraDetalleId> {
}
