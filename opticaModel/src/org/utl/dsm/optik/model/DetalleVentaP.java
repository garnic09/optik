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
public class DetalleVentaP {
    private Venta venta;
    private List<VentaProducto> listaVP;

    public DetalleVentaP() {
    }

    public DetalleVentaP(Venta venta, List<VentaProducto> listaVP) {
        this.venta = venta;
        this.listaVP = listaVP;
    }

    public List<VentaProducto> getListaVP() {
        return listaVP;
    }

    public void setListaVP(List<VentaProducto> listaVP) {
        this.listaVP = listaVP;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        String mensaje="";
        for (int i = 0; i < listaVP.size(); i++) {
            mensaje += listaVP.get(i).toString();
        }
        return "DetalleVentaP{" + "venta=" + venta.toString() + ", listaVP=" + mensaje + '}';
    }
    
}
