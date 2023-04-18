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
public class DetalleVentaPresupuestoL {
    private Venta venta;
    private List<VentaPresupuestoL> listVentaPresupuestoL;

    public DetalleVentaPresupuestoL() {
    }

    public DetalleVentaPresupuestoL(Venta venta, List<VentaPresupuestoL> listVentaPresupuestoL) {
        this.venta = venta;
        this.listVentaPresupuestoL = listVentaPresupuestoL;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<VentaPresupuestoL> getListVentaPresupuestoL() {
        return listVentaPresupuestoL;
    }

    public void setListVentaPresupuestoL(List<VentaPresupuestoL> listVentaPresupuestoL) {
        this.listVentaPresupuestoL = listVentaPresupuestoL;
    }

    @Override
    public String toString() {
        return "DetalleVentaPresupuestoL{" + "venta=" + venta + ", listVentaPresupuestoL=" + listVentaPresupuestoL + '}';
    }
    
    
}
