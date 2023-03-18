/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class Material {
    private int idMaterial;
    private String nombre;
    private float precioCompra;
    private float precioVenta;
    private int estatus;

    public Material() {
    }

    public Material(String nombre, float precioCompra, float precioVenta, int estatus) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.estatus = estatus;
    }

    public Material(int idMaterial, String nombre, float precioCompra, float precioVenta, int estatus) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.estatus = estatus;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
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

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString() {
        return "Material{" + "idMaterial=" + idMaterial + ", nombre=" + nombre + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", estatus=" + estatus + '}';
    }
    
    
}
