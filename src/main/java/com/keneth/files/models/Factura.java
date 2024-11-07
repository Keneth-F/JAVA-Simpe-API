package com.keneth.files.models;

import java.util.List;

public class Factura {

    private Person persona;
    private List<Producto> productos;
    private int total;

    public Factura() {
    }

    public Factura(Person persona, List<Producto> productos) {
        this.persona = persona;
        this.productos = productos;
        this.total = productos.stream().mapToInt(Producto::getPrecio).sum(); // Calcula el total sumando los precios de
                                                                             // los productos
    }

    public Person getPersona() {
        return persona;
    }

    public void setPersona(Person persona) {
        this.persona = persona;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
