package com.gestionempresa.repository;

import com.gestionempresa.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, String> {
}
