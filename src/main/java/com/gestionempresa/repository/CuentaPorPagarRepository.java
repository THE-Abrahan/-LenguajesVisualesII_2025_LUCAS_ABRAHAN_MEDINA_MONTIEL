package com.gestionempresa.repository;

import com.gestionempresa.model.CuentaPorPagar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaPorPagarRepository extends JpaRepository<CuentaPorPagar, CuentaPorPagar.Id> {
}
