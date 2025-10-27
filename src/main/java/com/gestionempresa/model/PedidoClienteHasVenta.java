package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "pedido_cliente_has_venta")
@Getter
@Setter
public class PedidoClienteHasVenta {

    @Embeddable
    @Getter
    @Setter
    public static class Id implements Serializable {
        @Column(name = "pedido_cliente_pedcli_id")
        private Integer pedcliId;
        @Column(name = "venta_ven_id")
        private Integer venId;
    }

    @EmbeddedId
    private Id id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_cliente_pedcli_id", insertable = false, updatable = false)
    private PedidoCliente pedidoCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_ven_id", insertable = false, updatable = false)
    private Venta venta;
}
