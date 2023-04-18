/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class TicketLente {
    //empleado
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
    private  String claveV;
    
    //Material
    private String nombreM;
    
    //Armazon
    private String nombreA;
    private String marca;
    
    //Tipo Mica
    private String nombreTP;
    
    //Examen Vista
    private String claveEV;
    private String fecha;
    //Tratamiento
    private String nombreT;
    
    //Presupuesto Lentes
    private int cantidad;
    private double precioUnitario;
    private double descuento;

    public TicketLente() {
    }

    public TicketLente(String nombreE, String apellidoPaternoE, String apellidoMaternoE, String numeroUnicoE, String nombreC, String apellidoPaternoC, String apellidoMaternoC, String numeroUnicoC, String claveV, String nombreM, String nombreA, String marca, String nombreTP, String claveEV, String fecha, String nombreT, int cantidad, double precioUnitario, double descuento) {
        this.nombreE = nombreE;
        this.apellidoPaternoE = apellidoPaternoE;
        this.apellidoMaternoE = apellidoMaternoE;
        this.numeroUnicoE = numeroUnicoE;
        this.nombreC = nombreC;
        this.apellidoPaternoC = apellidoPaternoC;
        this.apellidoMaternoC = apellidoMaternoC;
        this.numeroUnicoC = numeroUnicoC;
        this.claveV = claveV;
        this.nombreM = nombreM;
        this.nombreA = nombreA;
        this.marca = marca;
        this.nombreTP = nombreTP;
        this.claveEV = claveEV;
        this.fecha = fecha;
        this.nombreT = nombreT;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public TicketLente(String nombreE, String apellidoPaternoE, String apellidoMaternoE, String numeroUnicoE, String nombreC, String apellidoPaternoC, String apellidoMaternoC, String numeroUnicoC, String claveV, String nombreM, String nombreA, String marca, String nombreTP, String claveEV, String fecha, int cantidad, double precioUnitario, double descuento) {
        this.nombreE = nombreE;
        this.apellidoPaternoE = apellidoPaternoE;
        this.apellidoMaternoE = apellidoMaternoE;
        this.numeroUnicoE = numeroUnicoE;
        this.nombreC = nombreC;
        this.apellidoPaternoC = apellidoPaternoC;
        this.apellidoMaternoC = apellidoMaternoC;
        this.numeroUnicoC = numeroUnicoC;
        this.claveV = claveV;
        this.nombreM = nombreM;
        this.nombreA = nombreA;
        this.marca = marca;
        this.nombreTP = nombreTP;
        this.claveEV = claveEV;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
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

    public String getNombreM() {
        return nombreM;
    }

    public void setNombreM(String nombreM) {
        this.nombreM = nombreM;
    }

    public String getNombreA() {
        return nombreA;
    }

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombreTP() {
        return nombreTP;
    }

    public void setNombreTP(String nombreTP) {
        this.nombreTP = nombreTP;
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

    public String getNombreT() {
        return nombreT;
    }

    public void setNombreT(String nombreT) {
        this.nombreT = nombreT;
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

    @Override
    public String toString() {
        return "TicketLente{" + "nombreE=" + nombreE + ", apellidoPaternoE=" + apellidoPaternoE + ", apellidoMaternoE=" + apellidoMaternoE + ", numeroUnicoE=" + numeroUnicoE + ", nombreC=" + nombreC + ", apellidoPaternoC=" + apellidoPaternoC + ", apellidoMaternoC=" + apellidoMaternoC + ", numeroUnicoC=" + numeroUnicoC + ", claveV=" + claveV + ", nombreM=" + nombreM + ", nombreA=" + nombreA + ", marca=" + marca + ", nombreTP=" + nombreTP + ", claveEV=" + claveEV + ", fecha=" + fecha + ", nombreT=" + nombreT + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", descuento=" + descuento + '}';
    }
}
