/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.model.Tratamiento;

/**
 *
 * @author garni
 */
public class ControllerTratamiento {
    public int insertar(Tratamiento tratamiento) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call insertarTratamiento(?,?,?,?)";
        int idTratamientoG = 0;
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, tratamiento.getNombre());
        cstmt.setDouble(2, tratamiento.getPrecioCompra());
        cstmt.setDouble(3, tratamiento.getPrecioVenta());
        
        //Paso 5: Registrar parametros de salida del procedure
        cstmt.registerOutParameter(4, Types.INTEGER);
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        idTratamientoG = cstmt.getInt(4);
        
        // Paso 8 insertar los valores en el objeto 
        tratamiento.setIdTratamiento(idTratamientoG);
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
        // Paso 10 devolver o return el id generado
        return idTratamientoG;
    }
    
    public void actualizar(Tratamiento tratamiento) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call actualizarTratamiento(?,?,?,?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, tratamiento.getNombre());
        cstmt.setDouble(2, tratamiento.getPrecioCompra());
        cstmt.setDouble(3, tratamiento.getPrecioVenta());
        
        cstmt.setInt(4, tratamiento.getIdTratamiento());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
    }
    
    public void eliminar(Tratamiento tratamiento) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call eliminarTratamiento(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, tratamiento.getIdTratamiento());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
    }
    
    public void activar(Tratamiento tratamiento) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call activarTratamiento(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, tratamiento.getIdTratamiento());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
    }
    
    public List<Tratamiento> getAll(String filtro) throws SQLException{
        //Paso 1: Preparar la sentencia sql
        String query = "SELECT * FROM vista_tratamiento WHERE estatus = " + filtro + ";";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Tratamiento> tratamientos = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Tratamiento t = new Tratamiento();
            t.setIdTratamiento(rs.getInt("ID"));
            t.setEstatus(rs.getInt("Estatus"));
            t.setNombre(rs.getString("Nombre"));
            t.setPrecioCompra(rs.getDouble("Precio_Compra"));
            t.setPrecioVenta(rs.getDouble("Precio_Venta"));
            
            tratamientos.add(t);
        }
        rs.close();
        pstmt.close();
        conn.close();
        
        return tratamientos;
    }
}
