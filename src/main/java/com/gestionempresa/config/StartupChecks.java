package com.gestionempresa.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gestionempresa.repository.PedidoProveedorDetalleRepository;

@Component
@RequiredArgsConstructor
public class StartupChecks implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartupChecks.class);
    private final PedidoProveedorDetalleRepository pedidoProveedorDetalleRepository;

    @Override
    public void run(String... args) throws Exception {
        long count = pedidoProveedorDetalleRepository.count();
        log.info("Startup check: pedido_proveedor_detalle count = {}", count);
        // También imprimir en stdout para visibilidad rápida al arrancar desde IDE/terminal
        System.out.println("Startup check: pedido_proveedor_detalle count = " + count);
    }
}
