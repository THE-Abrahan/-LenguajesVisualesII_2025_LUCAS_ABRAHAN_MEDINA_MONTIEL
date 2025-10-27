package com.gestionempresa.repository;

import com.gestionempresa.model.PedidoProveedorDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repositorio para detalles de pedido a proveedor
public interface PedidoProveedorDetalleRepository extends JpaRepository<PedidoProveedorDetalle, Integer> {
	// Permite buscar detalles por pedido y producto (útil para eliminar cuando la UI solo envía pedprovId + productoId)
	List<PedidoProveedorDetalle> findByPedidoProveedor_IdAndProducto_Id(Integer pedprovId, String productoId);
}
