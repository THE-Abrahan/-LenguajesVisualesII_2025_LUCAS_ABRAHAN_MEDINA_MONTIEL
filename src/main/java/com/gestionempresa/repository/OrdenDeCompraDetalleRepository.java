package com.gestionempresa.repository;

import com.gestionempresa.model.OrdenDeCompraDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

// Se corrige el tipo de la clave primaria a Integer
public interface OrdenDeCompraDetalleRepository extends JpaRepository<OrdenDeCompraDetalle, Integer> {
}
