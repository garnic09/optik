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
public class PresupuestoL {
    private int idPresupuestoLentes;
    private Presupuesto presupuesto;
    private int alturaOblea;
    private TipoMica tipoMica;
    private Material material;
    private Armazon armazon;
    private List<Tratamiento> listTratamientos;
    
    public PresupuestoL() {
    }

    public PresupuestoL(Presupuesto presupuesto, int alturaOblea, TipoMica tipoMica, Material material, Armazon armazon, List<Tratamiento> listTratamientos) {
        this.presupuesto = presupuesto;
        this.alturaOblea = alturaOblea;
        this.tipoMica = tipoMica;
        this.material = material;
        this.armazon = armazon;
        this.listTratamientos = listTratamientos;
    }

    public PresupuestoL(int idPresupuestoLentes, Presupuesto presupuesto, int alturaOblea, TipoMica tipoMica, Material material, Armazon armazon, List<Tratamiento> listTratamientos) {
        this.idPresupuestoLentes = idPresupuestoLentes;
        this.presupuesto = presupuesto;
        this.alturaOblea = alturaOblea;
        this.tipoMica = tipoMica;
        this.material = material;
        this.armazon = armazon;
        this.listTratamientos = listTratamientos;
    }

    public int getIdPresupuestoLentes() {
        return idPresupuestoLentes;
    }

    public void setIdPresupuestoLentes(int idPresupuestoLentes) {
        this.idPresupuestoLentes = idPresupuestoLentes;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getAlturaOblea() {
        return alturaOblea;
    }

    public void setAlturaOblea(int alturaOblea) {
        this.alturaOblea = alturaOblea;
    }

    public TipoMica getTipoMica() {
        return tipoMica;
    }

    public void setTipoMica(TipoMica tipoMica) {
        this.tipoMica = tipoMica;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Armazon getArmazon() {
        return armazon;
    }

    public void setArmazon(Armazon armazon) {
        this.armazon = armazon;
    }

    public List<Tratamiento> getListTratamientos() {
        return listTratamientos;
    }

    public void setListTratamientos(List<Tratamiento> listTratamientos) {
        this.listTratamientos = listTratamientos;
    }

    @Override
    public String toString() {
        return "PresupuestoLentes{" + "idPresupuestoLentes=" + idPresupuestoLentes + ", presupuesto=" + presupuesto + ", alturaOblea=" + alturaOblea + ", tipoMica=" + tipoMica + ", material=" + material + ", armazon=" + armazon + ", listTratamientos=" + listTratamientos + '}';
    }

}
