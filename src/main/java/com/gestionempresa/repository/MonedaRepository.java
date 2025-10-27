package com.gestionempresa.repository;

import com.gestionempresa.model.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonedaRepository extends JpaRepository<Moneda, Integer> {
}

