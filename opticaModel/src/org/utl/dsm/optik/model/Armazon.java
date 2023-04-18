/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class Armazon {
    private int idArmazon;
    private Producto producto;
    private String modelo;
    private String color;
    private String dimensiones;
    private String descripcion;
    private String fotografia;

    public Armazon() {
    }
    //constructor para insertar todos los datos menos el id
    public Armazon(Producto producto, String modelo, String color, String dimensiones, String descripcion, String fotografia) {
        this.producto = producto;
        this.modelo = modelo;
        this.color = color;
        this.dimensiones = dimensiones;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
    }

    public Armazon(int idArmazon, Producto producto, String modelo, String color, String dimensiones, String descripcion, String fotografia) {
        this.idArmazon = idArmazon;
        this.producto = producto;
        this.modelo = modelo;
        this.color = color;
        this.dimensiones = dimensiones;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
    }

    public int getIdArmazon() {
        return idArmazon;
    }

    public void setIdArmazon(int idArmazon) {
        this.idArmazon = idArmazon;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    @Override
    public String toString() {
        return "Armazon{" +
                "idArmazon=" + idArmazon +
                ", producto=" + producto.toString() +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", dimensiones='" + dimensiones + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fotografia='" + fotografia + '\'' +
                '}';
    }
}

