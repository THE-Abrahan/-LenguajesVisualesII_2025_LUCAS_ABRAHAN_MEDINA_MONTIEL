package com.gestionempresa.repository;

import com.gestionempresa.dto.PresuPediResumen;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PresuPediJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public PresuPediJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PresuPediResumen> findAll() {
        String sql = "SELECT presupuesto_pres_id, pedido_cliente_pedcli_id FROM presu_pedi ORDER BY presupuesto_pres_id DESC LIMIT 100";
        return jdbcTemplate.query(sql, new RowMapper<PresuPediResumen>() {
            @Override
            public PresuPediResumen mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
                return new PresuPediResumen(rs.getInt("presupuesto_pres_id"), rs.getInt("pedido_cliente_pedcli_id"));
            }
        });
    }
}
