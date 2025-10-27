package com.gestionempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "compra_detalle")
@IdClass(CompraDetalleId.class) // Usar la clase de ID compuesta
@Getter
@Setter
public class CompraDetalle {

    @Id
    @Column(name = "comp_numerofactura")
    private String compNumerofactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_numerofactura", referencedColumnName = "comp_numerofactura", insertable = false, updatable = false)
    private Compra compra;
    
    @Id
    @Column(name = "Stock_Producto_prod_id")
    private String stockProductoProdId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Stock_Producto_prod_id", referencedColumnName = "prod_id", insertable = false, updatable = false)
    private Producto producto;

    @Column(name = "compdet_cantidad", precision = 5, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "compdet_preciounitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;
}
