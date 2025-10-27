package com.gestionempresa.repository;

import com.gestionempresa.model.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {
}
