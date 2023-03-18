/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import java.util.logging.Logger;
import java.util.logging.Level;
import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.utl.dsm.optik.model.Empleado;
import org.utl.dsm.optik.model.Persona;
import org.utl.dsm.optik.model.Usuario;
import org.utl.dsm.optik.model.TipoMica;
import org.utl.dsm.optik.model.ExamenVista;

/**
 *
 * @author garni
 */
public class prueba {

    public static void main(String[] args) {
        probarGetAll();
    }

    public static void probarConexion() {
        try {
            ConexionMySQL objConexion = new ConexionMySQL();
            Connection conexion = objConexion.open();
            System.out.println(conexion.toString());
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void probarInsert(){
        // Paso 1 genero un objeto de persona 
        Persona p = new Persona();
        p.setNombre("Gabriel");
        p.setApellidoPaterno("Ramos");
        p.setApellidoMaterno("Zacarias");
        p.setGenero("H");
        p.setFechaNacimiento("01/02/2003");
        p.setCalle("Via decollati");
        p.setNumero("112");
        p.setColonia("Villas de Palermo");
        p.setCp("37358");
        p.setCiudad("León");
        p.setEstado("Guanajuato");
        p.setTelcasa("12");
        p.setTelmovil("123");
        p.setEmail("iamramosg@gmail.com");
        
        // Crear un onjeto de usuario 
        Usuario u = new Usuario();
        u.setNombre("RamosG");
        u.setContrasenia("1234");
        u.setRol("Admin");
        
        Empleado emp = new Empleado();
        emp.setPersona(p);
        emp.setUsuario(u);
        
        
        // creamos una clase del objeto controllador empleado 
        ControllerEmpleado objCE = new ControllerEmpleado();
        try {
            objCE.insertar(emp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(emp.toString());
        
        
    }
    
    public static void probarUpdate(){
        // Paso 1 genero un objeto de persona 
        Persona p = new Persona();
        p.setIdPersona(6);
        p.setNombre("Xiomara");
        p.setApellidoPaterno("Ponce");
        p.setApellidoMaterno("Ponce");
        p.setGenero("F");
        p.setFechaNacimiento("2003/08/06");
        p.setCalle("Paracho");
        p.setNumero("112");
        p.setColonia("La Brisa");
        p.setCp("37358");
        p.setCiudad("León");
        p.setEstado("Guanajuato");
        p.setTelcasa("12");
        p.setTelmovil("123");
        p.setEmail("ponce@gmail.com");
        
        // Crear un onjeto de usuario 
        Usuario u = new Usuario();
        u.setIdUsuario(6);
        u.setNombre("PonceS");
        u.setContrasenia("1234");
        u.setRol("Gerente");
        
        Empleado emp = new Empleado();
        emp.setIdEmpleado(4);
        emp.setPersona(p);
        emp.setUsuario(u);
        
        
        
        // creamos una clase del objeto controllador empleado 
        ControllerEmpleado objCE = new ControllerEmpleado();
        try {
            objCE.actualizar(emp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(emp.toString());
    }
    
    public static void probarDelete(){
        // Paso 1 genero un objeto de persona 
        Empleado emp = new Empleado();
        emp.setIdEmpleado(1);
        
        // creamos una clase del objeto controllador empleado 
        ControllerEmpleado objCE = new ControllerEmpleado();
        try {
            objCE.eliminar(emp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(emp.toString());
    }
    
    public static void probarGetAll(){
        try {
            // Paso 1: Invocar el controller
            ControllerTipoMica objCe = new ControllerTipoMica();
            // Paso 2: Invocar el metodo
            List<TipoMica> tipoMicas = objCe.getAllMica();
            // Paso 3: Recorrer y mostrar los resultados
            for (int i = 0; i < tipoMicas.size(); i++) {
                System.out.println(tipoMicas.get(i).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void probarAcceso(){
        Usuario u = new Usuario();
        u.setNombre("Ramos");
        u.setContrasenia("1234");
        
        ControllerAcceso ca = new ControllerAcceso();
        try{
            Empleado e = ca.acceder(u);
            e.toString();
            System.out.println(e);
            System.out.println("Acceso concedido");
        }catch (Exception ex){
            System.out.println("Acceso denegado");
        }
    }
}
