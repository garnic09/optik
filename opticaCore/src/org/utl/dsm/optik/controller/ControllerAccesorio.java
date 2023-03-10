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
import org.utl.dsm.optik.model.Accesorio;
import org.utl.dsm.optik.model.Producto;

/**
 *
 * @author garni
 */
public class ControllerAccesorio {
    public int insertar(Accesorio accesorio) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call insertarAccesorio(?,?,?,?,?,?,?,?)";
        int idProductoG = 0;
        int idAccesorioG = 0;
        String codigoBarrasG = "";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, accesorio.getProducto().getNombre());
        cstmt.setString(2, accesorio.getProducto().getMarca());
        cstmt.setDouble(3, accesorio.getProducto().getPrecioCompra());
        cstmt.setDouble(4, accesorio.getProducto().getPrecioVenta());
        cstmt.setInt(5, accesorio.getProducto().getExistencias());
        
        //Paso 5: Registrar parametros de salida del procedure
        cstmt.registerOutParameter(6, Types.INTEGER);
        cstmt.registerOutParameter(7, Types.INTEGER);
        cstmt.registerOutParameter(8, Types.VARCHAR);
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        idProductoG = cstmt.getInt(6);
        idAccesorioG = cstmt.getInt(7);
        codigoBarrasG = cstmt.getString(8);
        
        // Paso 8 insertar los valores en el objeto 
        accesorio.getProducto().setIdProducto(idProductoG);
        accesorio.setIdAccesorio(idAccesorioG);
        accesorio.getProducto().setCodigoBarras(codigoBarrasG);
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
        // Paso 10 devolver o return el id generado
        return idAccesorioG;
    }
    
    public void actualizar(Accesorio accesorio) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call actualizarAccesorio(?,?,?,?,?,?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, accesorio.getProducto().getNombre());
        cstmt.setString(2, accesorio.getProducto().getMarca());
        cstmt.setDouble(3, accesorio.getProducto().getPrecioCompra());
        cstmt.setDouble(4, accesorio.getProducto().getPrecioVenta());
        cstmt.setInt(5, accesorio.getProducto().getExistencias());
        
        cstmt.setInt(6, accesorio.getProducto().getIdProducto());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
    }
    
    public void eliminar(Accesorio accesorio) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call eliminarAccesorio(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, accesorio.getIdAccesorio());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
    }
    
    public void activar(Accesorio accesorio) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call activarAccesorio(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, accesorio.getIdAccesorio());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
    }
    
    public List<Accesorio> getAll(String filtro) throws SQLException{
        //Paso 1: Preparar la sentencia sql
        String query = "SELECT * FROM vista_accesorio WHERE estatus = " + filtro + ";";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Accesorio> accesorios = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Accesorio a = new Accesorio();
            Producto p = new Producto();
            p.setIdProducto(rs.getInt("IDP"));
            p.setCodigoBarras(rs.getString("Codigo_Barras"));
            p.setEstatus(rs.getInt("Estatus"));
            p.setExistencias(rs.getInt("Existencias"));
            p.setMarca(rs.getString("Marca"));
            p.setNombre(rs.getString("Nombre"));
            p.setPrecioCompra(rs.getDouble("Precio_Compra"));
            p.setPrecioVenta(rs.getDouble("Precio_Venta"));
            a.setProducto(p);
            a.setIdAccesorio(rs.getInt("ID"));
            
            accesorios.add(a);
        }
        rs.close();
        pstmt.close();
        conn.close();
        
        return accesorios;
    }
}
