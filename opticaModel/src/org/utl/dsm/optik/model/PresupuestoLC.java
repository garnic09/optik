/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class PresupuestoLC {
    private int idPresupuestoLentesContacto;
    private String clave;
    private LenteContacto lenteContacto;
    private Presupuesto presupuesto;

    public PresupuestoLC() {
    }

    public PresupuestoLC(String clave, LenteContacto lenteContacto, Presupuesto presupuesto) {
        this.clave = clave;
        this.lenteContacto = lenteContacto;
        this.presupuesto = presupuesto;
    }

    public PresupuestoLC(int idPresupuestoLentesContacto, String clave, LenteContacto lenteContacto, Presupuesto presupuesto) {
        this.idPresupuestoLentesContacto = idPresupuestoLentesContacto;
        this.clave = clave;
        this.lenteContacto = lenteContacto;
        this.presupuesto = presupuesto;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getIdPresupuestoLentesContacto() {
        return idPresupuestoLentesContacto;
    }

    public void setIdPresupuestoLentesContacto(int idPresupuestoLentesContacto) {
        this.idPresupuestoLentesContacto = idPresupuestoLentesContacto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public LenteContacto getLenteContacto() {
        return lenteContacto;
    }

    public void setLenteContacto(LenteContacto lenteContacto) {
        this.lenteContacto = lenteContacto;
    }

    @Override
    public String toString() {
        return "PresupuestoLentesContacto{" + "idPresupuestoLentesContacto=" + idPresupuestoLentesContacto + ", clave=" + clave + ", lenteContacto=" + lenteContacto + ", presupuesto=" + presupuesto + '}';
    }
    
    
}
