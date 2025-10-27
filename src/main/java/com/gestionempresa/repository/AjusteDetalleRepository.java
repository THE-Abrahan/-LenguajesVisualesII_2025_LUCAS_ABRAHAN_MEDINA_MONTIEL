package com.gestionempresa.repository;

import com.gestionempresa.model.AjusteDetalle;
import com.gestionempresa.model.AjusteDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AjusteDetalleRepository extends JpaRepository<AjusteDetalle, AjusteDetalleId> {
}
