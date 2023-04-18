/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class TicketLenteC {
    //Empleado
    private String nombreE;
    private String apellidoPaternoE;
    private String apellidoMaternoE;
    private String numeroUnicoE;

    //Cliente 
    private String nombreC;
    private String apellidoPaternoC;
    private String apellidoMaternoC;
    private String numeroUnicoC;

    //Venta 
    private String claveV;

    //Examen Vista
    private String claveEV;
    private String fecha;

    //Presupuesto Lentes
    private int cantidad;
    private double precioUnitario;
    private double descuento;

    //Graduacion
    private double esferaod;
    private double esferaoi;
    private int cilindrood;
    private int cilindrooi;
    private int ejeoi;
    private int ejeod;
    private String dip;

    //Lente contacto
    private int keratometria;
    private String nombreP;
    private String marca;

    public TicketLenteC() {
    }

    public TicketLenteC(String nombreE, String apellidoPaternoE, String apellidoMaternoE, String numeroUnicoE, String nombreC, String apellidoPaternoC, String apellidoMaternoC, String numeroUnicoC, String claveV, String claveEV, String fecha, int cantidad, double precioUnitario, double descuento, double esferaod, double esferaoi, int cilindrood, int cilindrooi, int ejeoi, int ejeod, String dip, int keratometria, String nombreP, String marca) {
        this.nombreE = nombreE;
        this.apellidoPaternoE = apellidoPaternoE;
        this.apellidoMaternoE = apellidoMaternoE;
        this.numeroUnicoE = numeroUnicoE;
        this.nombreC = nombreC;
        this.apellidoPaternoC = apellidoPaternoC;
        this.apellidoMaternoC = apellidoMaternoC;
        this.numeroUnicoC = numeroUnicoC;
        this.claveV = claveV;
        this.claveEV = claveEV;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.esferaod = esferaod;
        this.esferaoi = esferaoi;
        this.cilindrood = cilindrood;
        this.cilindrooi = cilindrooi;
        this.ejeoi = ejeoi;
        this.ejeod = ejeod;
        this.dip = dip;
        this.keratometria = keratometria;
        this.nombreP = nombreP;
        this.marca = marca;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getApellidoPaternoE() {
        return apellidoPaternoE;
    }

    public void setApellidoPaternoE(String apellidoPaternoE) {
        this.apellidoPaternoE = apellidoPaternoE;
    }

    public String getApellidoMaternoE() {
        return apellidoMaternoE;
    }

    public void setApellidoMaternoE(String apellidoMaternoE) {
        this.apellidoMaternoE = apellidoMaternoE;
    }

    public String getNumeroUnicoE() {
        return numeroUnicoE;
    }

    public void setNumeroUnicoE(String numeroUnicoE) {
        this.numeroUnicoE = numeroUnicoE;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getApellidoPaternoC() {
        return apellidoPaternoC;
    }

    public void setApellidoPaternoC(String apellidoPaternoC) {
        this.apellidoPaternoC = apellidoPaternoC;
    }

    public String getApellidoMaternoC() {
        return apellidoMaternoC;
    }

    public void setApellidoMaternoC(String apellidoMaternoC) {
        this.apellidoMaternoC = apellidoMaternoC;
    }

    public String getNumeroUnicoC() {
        return numeroUnicoC;
    }

    public void setNumeroUnicoC(String numeroUnicoC) {
        this.numeroUnicoC = numeroUnicoC;
    }

    public String getClaveV() {
        return claveV;
    }

    public void setClaveV(String claveV) {
        this.claveV = claveV;
    }

    public String getClaveEV() {
        return claveEV;
    }

    public void setClaveEV(String claveEV) {
        this.claveEV = claveEV;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
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

    public int getEjeoi() {
        return ejeoi;
    }

    public void setEjeoi(int ejeoi) {
        this.ejeoi = ejeoi;
    }

    public int getEjeod() {
        return ejeod;
    }

    public void setEjeod(int ejeod) {
        this.ejeod = ejeod;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public int getKeratometria() {
        return keratometria;
    }

    public void setKeratometria(int keratometria) {
        this.keratometria = keratometria;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "TicketLenteC{" + "nombreE=" + nombreE + ", apellidoPaternoE=" + apellidoPaternoE + ", apellidoMaternoE=" + apellidoMaternoE + ", numeroUnicoE=" + numeroUnicoE + ", nombreC=" + nombreC + ", apellidoPaternoC=" + apellidoPaternoC + ", apellidoMaternoC=" + apellidoMaternoC + ", numeroUnicoC=" + numeroUnicoC + ", claveV=" + claveV + ", claveEV=" + claveEV + ", fecha=" + fecha + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", descuento=" + descuento + ", esferaod=" + esferaod + ", esferaoi=" + esferaoi + ", cilindrood=" + cilindrood + ", cilindrooi=" + cilindrooi + ", ejeoi=" + ejeoi + ", ejeod=" + ejeod + ", dip=" + dip + ", keratometria=" + keratometria + ", nombreP=" + nombreP + ", marca=" + marca + '}';
    }
}
