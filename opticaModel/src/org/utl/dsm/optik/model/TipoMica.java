/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class TipoMica {
    private int idTipoMica;
    private String nombre;
    private float precioCompra;
    private float precioVenta;

    public TipoMica() {
    }

    public TipoMica(String nombre, float precioCompra, float precioVenta) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public TipoMica(int idTipoMica, String nombre, float precioCompra, float precioVenta) {
        this.idTipoMica = idTipoMica;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getIdTipoMica() {
        return idTipoMica;
    }

    public void setIdTipoMica(int idTipoMica) {
        this.idTipoMica = idTipoMica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    @Override
    public String toString() {
        return "TipoMica{" + "idTipoMica=" + idTipoMica + ", nombre=" + nombre + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + '}';
    }
    
    
}
