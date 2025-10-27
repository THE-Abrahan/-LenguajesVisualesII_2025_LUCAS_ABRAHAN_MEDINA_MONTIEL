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
@Table(name = "ajustes_detalle")
@IdClass(AjusteDetalleId.class)
@Getter
@Setter
public class AjusteDetalle {

    @Id
    @Column(name = "Ajustes_ajus_id")
    private Integer ajusId;

    @Id
    @Column(name = "prod_id")
    private String prodId;

    @Id
    @Column(name = "ajustip_id")
    private Integer ajustipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ajustes_ajus_id", insertable = false, updatable = false)
    private Ajuste ajuste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id", insertable = false, updatable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ajustip_id", referencedColumnName = "ajustip_id", insertable = false, updatable = false)
    private AjusteTipo tipo;

    @Column(name = "detajustes_cantidad", precision = 5, scale = 2)
    private BigDecimal cantidad;
}
