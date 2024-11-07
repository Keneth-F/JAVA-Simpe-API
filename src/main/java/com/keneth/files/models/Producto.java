package com.keneth.files.models;

public class Producto {

    private String nombre;
    private String desc;
    private int precio;

    public Producto() {
        super();
    }

    public Producto(String nombre, String desc, int precio) {
        this.nombre = nombre;
        this.desc = desc;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}
