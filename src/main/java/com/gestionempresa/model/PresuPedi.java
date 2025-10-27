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

@Entity
@Table(name = "presu_pedi")
@IdClass(PresuPediId.class)
@Getter
@Setter
public class PresuPedi {

    @Id
    @Column(name = "presupuesto_pres_id")
    private Integer presupuestoPresId;

    @Id
    @Column(name = "pedido_cliente_pedcli_id")
    private Integer pedidoClientePedcliId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presupuesto_pres_id", insertable = false, updatable = false)
    private Presupuesto presupuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_cliente_pedcli_id", insertable = false, updatable = false)
    private PedidoCliente pedidoCliente;
}