package com.gestionempresa.repository;

import com.gestionempresa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

// Producto uses String as its @Id (prod_id VARCHAR(13)), so repository must use String
public interface ProductoRepository extends JpaRepository<Producto, String> {
}
