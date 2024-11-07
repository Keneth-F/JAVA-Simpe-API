package com.keneth.files.models;

import java.util.List;

public class FacturaDTO {
    private String cedula; // La cedula de la persona
    private List<String> productosIds; // Lista de IDs de los productos seleccionados

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<String> getProductosIds() {
        return productosIds;
    }

    public void setProductosIds(List<String> productosIds) {
        this.productosIds = productosIds;
    }
}
