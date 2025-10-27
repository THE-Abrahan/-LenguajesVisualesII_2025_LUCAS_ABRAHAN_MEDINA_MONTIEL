package com.gestionempresa.repository;

import com.gestionempresa.model.PedidoClienteHasVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoClienteHasVentaRepository extends JpaRepository<PedidoClienteHasVenta, PedidoClienteHasVenta.Id> {
}

