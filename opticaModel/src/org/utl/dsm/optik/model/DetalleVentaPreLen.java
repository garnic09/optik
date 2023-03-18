/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;
import java.util.List;
/**
 *
 * @author iamra
 */
public class DetalleVentaPreLen {
    private Venta venta;
    private List<VentaPresupuestoL> listaVentaPresupuestoL;

    public DetalleVentaPreLen() {
    }

    public DetalleVentaPreLen(Venta venta, List<VentaPresupuestoL> listaVentaPresupuestoL) {
        this.venta = venta;
        this.listaVentaPresupuestoL = listaVentaPresupuestoL;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<VentaPresupuestoL> getListaVentaPresupuestoL() {
        return listaVentaPresupuestoL;
    }

    public void setListaVentaPresupuestoL(List<VentaPresupuestoL> listaVentaPresupuestoL) {
        this.listaVentaPresupuestoL = listaVentaPresupuestoL;
    }

    @Override
    public String toString() {
        return "DetalleVentaPreLen{" + "venta=" + venta + ", listaVentaPresupuestoL=" + listaVentaPresupuestoL + '}';
    }
    
    
}
