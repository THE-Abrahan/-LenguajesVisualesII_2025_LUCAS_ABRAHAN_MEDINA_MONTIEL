package com.gestionempresa.repository;

import com.gestionempresa.model.PedidoClienteDetalle;
import com.gestionempresa.model.PedidoClienteDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoClienteDetalleRepository extends JpaRepository<PedidoClienteDetalle, PedidoClienteDetalleId> {
}
