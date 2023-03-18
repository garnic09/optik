/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class GraduacionLentes {
    private int idGraduacion;
    private double esferaod;
    private double esferaoi;
    private int cilindrood;
    private int cilindrooi;
    private int ejeod;
    private int ejeoi;
    private String dip;
    private int estatus;

    public GraduacionLentes() {
    }

    public GraduacionLentes(double esferaod, double esferaoi, int cilindrood, int cilindrooi, int ejeod, int ejeoi, String dip, int estatus) {
        this.esferaod = esferaod;
        this.esferaoi = esferaoi;
        this.cilindrood = cilindrood;
        this.cilindrooi = cilindrooi;
        this.ejeod = ejeod;
        this.ejeoi = ejeoi;
        this.dip = dip;
        this.estatus = estatus;
    }

    public GraduacionLentes(int idGraduacion) {
        this.idGraduacion = idGraduacion;
    }
    

    public GraduacionLentes(int idGraduacion, double esferaod, double esferaoi, int cilindrood, int cilindrooi, int ejeod, int ejeoi, String dip, int estatus) {
        this.idGraduacion = idGraduacion;
        this.esferaod = esferaod;
        this.esferaoi = esferaoi;
        this.cilindrood = cilindrood;
        this.cilindrooi = cilindrooi;
        this.ejeod = ejeod;
        this.ejeoi = ejeoi;
        this.dip = dip;
        this.estatus = estatus;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdGraduacion() {
        return idGraduacion;
    }

    public void setIdGraduacion(int idGraduacion) {
        this.idGraduacion = idGraduacion;
    }

    public double getEsferaod() {
        return esferaod;
    }

    public void setEsferaod(double esferaod) {
        this.esferaod = esferaod;
    }

    public double getEsferaoi() {
        return esferaoi;
    }

    public void setEsferaoi(double esferaoi) {
        this.esferaoi = esferaoi;
    }

    public int getCilindrood() {
        return cilindrood;
    }

    public void setCilindrood(int cilindrood) {
        this.cilindrood = cilindrood;
    }

    public int getCilindrooi() {
        return cilindrooi;
    }

    public void setCilindrooi(int cilindrooi) {
        this.cilindrooi = cilindrooi;
    }

    public int getEjeod() {
        return ejeod;
    }

    public void setEjeod(int ejeod) {
        this.ejeod = ejeod;
    }

    public int getEjeoi() {
        return ejeoi;
    }

    public void setEjeoi(int ejeoi) {
        this.ejeoi = ejeoi;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    @Override
    public String toString() {
        return "GraduacionLentes{" + "idGraduacion=" + idGraduacion + ", esferaod=" + esferaod + ", esferaoi=" + esferaoi + ", cilindrood=" + cilindrood + ", cilindrooi=" + cilindrooi + ", ejeod=" + ejeod + ", ejeoi=" + ejeoi + ", dip=" + dip + ", estatus=" + estatus + '}';
    }
    
    
}
