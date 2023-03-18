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
import org.utl.dsm.optik.model.Material;

/**
 *
 * @author garni
 */
public class ControllerMaterial {
    public int insertar(Material material) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call insertarMaterial(?,?,?,?)";
        int idMaterialG = 0;
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, material.getNombre());
        cstmt.setDouble(2, material.getPrecioCompra());
        cstmt.setDouble(3, material.getPrecioVenta());
        
        //Paso 5: Registrar parametros de salida del procedure
        cstmt.registerOutParameter(4, Types.INTEGER);
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        idMaterialG = cstmt.getInt(4);
        
        // Paso 8 insertar los valores en el objeto 
        material.setIdMaterial(idMaterialG);
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
        // Paso 10 devolver o return el id generado
        return idMaterialG;
    }
    
    public void actualizar(Material material) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call actualizarMaterial(?,?,?,?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, material.getNombre());
        cstmt.setDouble(2, material.getPrecioCompra());
        cstmt.setDouble(3, material.getPrecioVenta());
        
        cstmt.setInt(4, material.getIdMaterial());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
    }
    
    public void eliminar(Material material) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call eliminarMaterial(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, material.getIdMaterial());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
    }
    
    public void activar(Material material) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call activarMaterial(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, material.getIdMaterial());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
    }
    
    public List<Material> getAll(String filtro) throws SQLException{
        //Paso 1: Preparar la sentencia sql
        String query = "SELECT * FROM vista_material WHERE estatus = " + filtro + ";";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Material> materiales = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Material t = new Material();
            t.setIdMaterial(rs.getInt("ID"));
            t.setEstatus(rs.getInt("Estatus"));
            t.setNombre(rs.getString("Nombre"));
            t.setPrecioCompra(rs.getFloat("Precio_Compra"));
            t.setPrecioVenta(rs.getFloat("Precio_Venta"));
            
            materiales.add(t);
        }
        rs.close();
        pstmt.close();
        conn.close();
        
        return materiales;
    }
}
