package com.gestionempresa.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Configuration
@Profile("db-introspect")
public class DbIntrospectionRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DbIntrospectionRunner.class);

    private final DataSource dataSource;

    public DbIntrospectionRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] tables = new String[]{
                "proveedor",
                "cobro_detalle",
                "compra_detalle",
                "pedido_cliente_detalle",
                "presupuesto_detalle",
                "venta_detalle"
        };

        try (Connection conn = dataSource.getConnection(); Statement st = conn.createStatement()) {
            for (String t : tables) {
                try {
                    log.info("--- SHOW CREATE TABLE {} ---", t);
                    ResultSet rs = st.executeQuery("SHOW CREATE TABLE `" + t + "`");
                    while (rs.next()) {
                        String create = rs.getString(2);
                        log.info(create);
                    }
                    rs.close();
                } catch (Exception e) {
                    log.warn("No se pudo obtener SHOW CREATE TABLE {}: {}", t, e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Error conectando a la BD para introspección: {}", e.getMessage(), e);
            // rethrow so startup still fails visibly if connection not available
            throw e;
        }
    }
}
