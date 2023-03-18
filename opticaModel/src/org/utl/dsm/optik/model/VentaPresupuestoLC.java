/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class VentaPresupuestoLC {
    private PresupuestoLC presupuestoVentaLC;
    private int cantidad;
    private float precioUnitario;
    private float descuento;

    public VentaPresupuestoLC() {
    }

    public VentaPresupuestoLC(PresupuestoLC presupuestoVentaLC, int cantidad, float precioUnitario, float descuento) {
        this.presupuestoVentaLC = presupuestoVentaLC;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public PresupuestoLC getPresupuestoVentaLC() {
        return presupuestoVentaLC;
    }

    public void setPresupuestoVentaLC(PresupuestoLC presupuestoVentaLC) {
        this.presupuestoVentaLC = presupuestoVentaLC;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "VentaPresupuestoLentesContacto{" + ", presupuestoVentaLC=" + presupuestoVentaLC + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", descuento=" + descuento + '}';
    }
    
    
}
