/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

import java.util.List;

/**
 *
 * @author garni
 */
public class DetalleVentaPresupuestoLC {
    private Venta venta;
    private List<VentaPresupuestoLC> listVentaPresupuestoLC;

    public DetalleVentaPresupuestoLC() {
    }

    public DetalleVentaPresupuestoLC(Venta venta, List<VentaPresupuestoLC> listVentaPresupuestoLC) {
        this.venta = venta;
        this.listVentaPresupuestoLC = listVentaPresupuestoLC;
    }

    public List<VentaPresupuestoLC> getListVentaPresupuestoLC() {
        return listVentaPresupuestoLC;
    }

    public void setListVentaPresupuestoLC(List<VentaPresupuestoLC> listVentaPresupuestoLC) {
        this.listVentaPresupuestoLC = listVentaPresupuestoLC;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        return "DetalleVentaPresupuestoLentesContacto{" + "venta=" + venta + ", listVentaPresupuestoLC=" + listVentaPresupuestoLC + '}';
    }
    
}
