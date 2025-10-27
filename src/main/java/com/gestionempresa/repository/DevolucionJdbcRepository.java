package com.gestionempresa.repository;

import com.gestionempresa.dto.DevolucionResumen;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DevolucionJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public DevolucionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DevolucionResumen> findResumen() {
        String sql = "SELECT devprov_id, NULL AS comp_numfactura, devprov_fecha FROM devolucion_a_proveedor ORDER BY devprov_id DESC LIMIT 50";
        return jdbcTemplate.query(sql, new RowMapper<DevolucionResumen>() {
            @Override
            public DevolucionResumen mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
                return new DevolucionResumen(rs.getInt("devprov_id"), rs.getString("comp_numfactura"), rs.getString("devprov_fecha"));
            }
        });
    }
}
