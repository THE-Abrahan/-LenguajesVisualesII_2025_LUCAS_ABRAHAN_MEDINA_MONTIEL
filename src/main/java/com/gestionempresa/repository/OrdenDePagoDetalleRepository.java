package com.gestionempresa.repository;

import com.gestionempresa.model.OrdenDePagoDetalle;
import com.gestionempresa.model.OrdenDePagoDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenDePagoDetalleRepository extends JpaRepository<OrdenDePagoDetalle, OrdenDePagoDetalleId> {
}

