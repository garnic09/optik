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
    
    //Constructores
    public Accesorio() {
    }

    public Accesorio(Producto producto) {
        this.producto = producto;
    }

    public Accesorio(int idAccesorio, Producto idProducto) {
        this.idAccesorio = idAccesorio;
        this.producto = idProducto;
    }

    //Getters y setters
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

    @Override
    public String toString() {
        return "Accesorio{" + "idAccesorio=" + idAccesorio + ", producto=" + producto + '}';
    }
}
