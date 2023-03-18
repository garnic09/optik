/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class ExamenVista {
    private int idExamenVista;
    private String clave;
    private Empleado empleado;
    private Cliente cliente;
    private GraduacionLentes graduacion;
    private String fecha;

    public ExamenVista() {
    }

    public ExamenVista(String clave, Empleado empleado, Cliente cliente, GraduacionLentes graduacion, String fecha) {
        this.clave = clave;
        this.empleado = empleado;
        this.cliente = cliente;
        this.graduacion = graduacion;
        this.fecha = fecha;
    }

    public ExamenVista(int idExamenVista, String clave, Empleado empleado, Cliente cliente, GraduacionLentes graduacion, String fecha) {
        this.idExamenVista = idExamenVista;
        this.clave = clave;
        this.empleado = empleado;
        this.cliente = cliente;
        this.graduacion = graduacion;
        this.fecha = fecha;
    }

    public int getIdExamenVista() {
        return idExamenVista;
    }

    public void setIdExamenVista(int idExamenVista) {
        this.idExamenVista = idExamenVista;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public GraduacionLentes getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(GraduacionLentes graduacion) {
        this.graduacion = graduacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ExamenVista{" + "idExamenVista=" + idExamenVista + ", clave=" + clave + ", empleado=" + empleado + ", cliente=" + cliente + ", graduacion=" + graduacion + ", fecha=" + fecha + '}';
    }

 
    
    
}
