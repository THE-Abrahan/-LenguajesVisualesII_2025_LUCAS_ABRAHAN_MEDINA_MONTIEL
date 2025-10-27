package com.gestionempresa.dto;

public class PresuPediResumen {
    private Integer presId;
    private Integer pedcliId;

    public PresuPediResumen(Integer presId, Integer pedcliId) {
        this.presId = presId;
        this.pedcliId = pedcliId;
    }

    public Integer getPresId() { return presId; }
    public Integer getPedcliId() { return pedcliId; }
}
