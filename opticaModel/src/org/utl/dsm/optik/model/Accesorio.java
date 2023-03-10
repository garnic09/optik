/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class Accesorio {
    private int idAccesorio;
    private Producto producto;

    public Accesorio() {
    }

    public Accesorio(Producto producto) {
        this.producto = producto;
    }

    public Accesorio(int idAccesorio, Producto idProducto) {
        this.idAccesorio = idAccesorio;
        this.producto = idProducto;
    }

    public int getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(int idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
