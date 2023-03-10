/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author diana
 */public class Solucion {
    private int idSolucion;
    private Producto producto;public Solucion() {
    }

    public Solucion(Producto producto) {
        this.producto = producto;
    }

    public Solucion(int idSolucion, Producto idProducto) {
        this.idSolucion = idSolucion;
        this.producto = idProducto;
    }

    public int getIdSolucion() {
        return idSolucion;
    }

    public void setIdSolucion(int idSolucion) {
        this.idSolucion = idSolucion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
