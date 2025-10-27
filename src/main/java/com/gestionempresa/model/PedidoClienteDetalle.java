package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "pedido_cliente_detalle")
@IdClass(PedidoClienteDetalleId.class)
@Getter
@Setter
public class PedidoClienteDetalle {

    @Id
    @Column(name = "Pedido_cliente_pedcli_id")
    private Integer pedcliId;

    @Id
    @Column(name = "Stock_Producto_prod_id")
    private String stockProductoProdId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Pedido_cliente_pedcli_id", insertable = false, updatable = false)
    private PedidoCliente pedidoCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Stock_Producto_prod_id", referencedColumnName = "prod_id", insertable = false, updatable = false)
    private Producto producto;

    @Column(name = "pedclidet_cantidad", precision = 5, scale = 2)
    private BigDecimal pedcliDetCantidad;

    @Column(name = "pedclidet_preciounitario", precision = 12, scale = 2)
    private BigDecimal pedcliDetPrecioUnitario;
}
