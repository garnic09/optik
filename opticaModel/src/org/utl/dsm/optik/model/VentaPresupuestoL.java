/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class VentaPresupuestoL {
    PresupuestoL presupuestoLente;
    int cantidad;
    float precioUnitario;
    float descuento;

    public VentaPresupuestoL() {
    }

    public VentaPresupuestoL(PresupuestoL presupuestoLente, int cantidad, float precioUnitario, float descuento) {
        this.presupuestoLente = presupuestoLente;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public PresupuestoL getPresupuestoLente() {
        return presupuestoLente;
    }

    public void setPresupuestoLente(PresupuestoL presupuestoLente) {
        this.presupuestoLente = presupuestoLente;
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

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "VentaProsupuestoL{" + "presupuestoLente=" + presupuestoLente + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", descuento=" + descuento + '}';
    }

}
