package com.gestionempresa.repository;

import com.gestionempresa.model.CuentaPorCobrar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CuentaPorCobrarRepository extends JpaRepository<CuentaPorCobrar, CuentaPorCobrar.Id> {

    @Transactional
    @Modifying
    @Query("delete from CuentaPorCobrar c where c.id.venId = :venId and c.id.cuotaNumero = :cuota")
    void deleteByVenIdAndCuota(Integer venId, Integer cuota);
}
