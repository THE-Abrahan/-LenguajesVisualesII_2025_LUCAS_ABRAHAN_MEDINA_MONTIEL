package com.gestionempresa.repository;

import com.gestionempresa.model.CobroDetalle;
import com.gestionempresa.model.CobroDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobroDetalleRepository extends JpaRepository<CobroDetalle, CobroDetalleId> {
}
