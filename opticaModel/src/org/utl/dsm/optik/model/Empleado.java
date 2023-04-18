package org.utl.dsm.optik.model;

/**
 * @author garni
 */
public class Empleado {
    private int idEmpleado;
    private String numeroUnico;
    private int estatus;
    private Persona persona;
    private Usuario usuario;

    public Empleado() {
    }

    public Empleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(String numeroUnico, int estatus, Persona persona, Usuario usuario) {
        this.numeroUnico = numeroUnico;
        this.estatus = estatus;
        this.persona = persona;
        this.usuario = usuario;
    }

    public Empleado(int idEmpleado, String numeroUnico, int estatus, Persona persona, Usuario usuario) {
        this.idEmpleado = idEmpleado;
        this.numeroUnico = numeroUnico;
        this.estatus = estatus;
        this.persona = persona;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNumeroUnico() {
        return numeroUnico;
    }

    public void setNumeroUnico(String numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", numeroUnico=" + numeroUnico + ", estatus=" + estatus + ", persona=" + persona.toString() + ", usuario=" + usuario.toString() + '}';
    }
    
}
