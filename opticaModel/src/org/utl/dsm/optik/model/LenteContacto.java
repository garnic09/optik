/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author garni
 */
public class LenteContacto {
    private int idLenteContacto;
    private Producto producto;
    private String keratometria;
    private String foto;
    private String tipo;

    public LenteContacto() {
    }

    public LenteContacto(int idLenteContacto) {
        this.idLenteContacto = idLenteContacto;
    }

    public LenteContacto(Producto producto, String keratometria, String foto, String tipo) {
        this.producto = producto;
        this.keratometria = keratometria;
        this.foto = foto;
        this.tipo = tipo;
    }

    public LenteContacto(int idLenteContacto, Producto producto, String keratometria, String foto, String tipo) {
        this.idLenteContacto = idLenteContacto;
        this.producto = producto;
        this.keratometria = keratometria;
        this.foto = foto;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdLenteContacto() {
        return idLenteContacto;
    }

    public void setIdLenteContacto(int idLenteContacto) {
        this.idLenteContacto = idLenteContacto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getKeratometria() {
        return keratometria;
    }

    public void setKeratometria(String keratometria) {
        this.keratometria = keratometria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "LenteContacto{" + "idLenteContacto=" + idLenteContacto + ", producto=" + producto + ", keratometria=" + keratometria + ", foto=" + foto + ", tipo=" + tipo + '}';
    }
    
}
