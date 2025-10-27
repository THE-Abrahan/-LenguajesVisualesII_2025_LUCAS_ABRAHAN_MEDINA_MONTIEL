package com.gestionempresa.repository;

import com.gestionempresa.model.PedidoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoClienteRepository extends JpaRepository<PedidoCliente, Integer> {
}
