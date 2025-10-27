package com.gestionempresa.dto;

public class DevolucionResumen {
    private Integer id;
    private String compNumfactura;
    private String fecha;

    public DevolucionResumen(Integer id, String compNumfactura, String fecha) {
        this.id = id;
        this.compNumfactura = compNumfactura;
        this.fecha = fecha;
    }

    public Integer getId() { return id; }
    public String getCompNumfactura() { return compNumfactura; }
    public String getFecha() { return fecha; }
}
