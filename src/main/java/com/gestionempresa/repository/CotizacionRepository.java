package com.gestionempresa.repository;

import com.gestionempresa.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
}
