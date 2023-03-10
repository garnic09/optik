/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

/**
 *
 * @author garni
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.model.Armazon;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.utl.dsm.optik.model.Producto;

/**
 *
 * @author ferna13
 */
public class ControllerArmazon {
    public int insertar(Armazon armazon) throws Exception {
        String query = "call insertarArmazon(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int idProductoG = 0;
        int idArmazonG = 0;
        String codigoBarrasG = "";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        cstmt.setString(1, armazon.getProducto().getNombre());
        cstmt.setString(2, armazon.getProducto().getMarca());
        cstmt.setDouble(3, armazon.getProducto().getPrecioCompra());
        cstmt.setDouble(4, armazon.getProducto().getPrecioVenta());
        cstmt.setInt(5, armazon.getProducto().getExistencias());
        
        
        cstmt.setString(6, armazon.getModelo());
        cstmt.setString(7, armazon.getColor());
        cstmt.setString(8, armazon.getDimensiones());
        cstmt.setString(9, armazon.getDescripcion());
        cstmt.setString(10, armazon.getFotografia());
        
        cstmt.registerOutParameter(11, Types.INTEGER);
        cstmt.registerOutParameter(12, Types.INTEGER);
        cstmt.registerOutParameter(13, Types.VARCHAR);
        
        cstmt.executeUpdate();
        
        idProductoG = cstmt.getInt(11);
        idArmazonG = cstmt.getInt(12);
        codigoBarrasG = cstmt.getString(13);
        
        armazon.getProducto().setIdProducto(idProductoG);
        armazon.setIdArmazon(idArmazonG);
        armazon.getProducto().setCodigoBarras(codigoBarrasG);
        cstmt.close();
        conn.close();
        conexion.close();
        
        return idArmazonG;
        
    }
    
    
    
    
    public void actualizar(Armazon armazon) throws Exception{
        //Preparar la sentencia sql
        String query = "call actualizarArmazon(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
         //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //cstmt.setString(1, armazon.getProducto().getCodigoBarras());
        cstmt.setString(1, armazon.getProducto().getNombre());
        cstmt.setString(2, armazon.getProducto().getMarca());
        cstmt.setDouble(3, armazon.getProducto().getPrecioCompra());
        cstmt.setDouble(4, armazon.getProducto().getPrecioVenta());
        cstmt.setInt(5, armazon.getProducto().getExistencias());
        
        cstmt.setString(6, armazon.getModelo());
        cstmt.setString(7, armazon.getColor());
        cstmt.setString(8, armazon.getDimensiones());
        cstmt.setString(9, armazon.getDescripcion());
        cstmt.setString(10, armazon.getFotografia());
       
        cstmt.setInt(11 , armazon.getProducto().getIdProducto());
        cstmt.setInt(12,armazon.getIdArmazon());
        
         //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        
        
        // Paso 8 insertar los valores en el objeto 
        
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
        // Paso 10 devolver o return el id generado
    }
    
    
      public List<Armazon> getAll (String filtro) throws SQLException{
        String query="SELECT * FROM vista_Armazon WHERE estatus="+filtro+";";
        ConexionMySQL objConexion= new ConexionMySQL();
        Connection conn=objConexion.open();
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Armazon> armazones = new ArrayList<>();
        ResultSet rs= pstmt.executeQuery();
        while(rs.next()){
           
            Producto p = new Producto();
            
            /*PRODUCTO*/
            p.setCodigoBarras(rs.getString("codigoBarras"));
            p.setNombre(rs.getString("nombre"));
            p.setMarca(rs.getString("marca"));
            p.setPrecioCompra(rs.getDouble("precioCompra"));
            p.setPrecioVenta(rs.getDouble("precioVenta"));
            p.setExistencias(rs.getInt("existencias"));
            p.setIdProducto(rs.getInt("idProducto"));
           
             Armazon a = new Armazon();
            /*ARMAZON*/
            a.setProducto(p);
            a.setIdArmazon(rs.getInt("idArmazon"));
            a.setModelo(rs.getString("modelo"));
            a.setColor(rs.getString("color"));
            a.setDimensiones(rs.getString("dimensiones"));
            a.setDescripcion(rs.getString("descripcion"));
            a.setFotografia(rs.getString("fotografia"));
            a.setEstatus(rs.getInt("estatus"));
            
            armazones.add(a);
            
            
            
//            a.setProducto(p);
//           a.setIdArmazon(rs.getInt("idArmazon"));
//            armazones.add(a);

        }
        rs.close();
        pstmt.close();
        conn.close();
        return armazones;
    }
    
    
    public void estatusInactivo(Armazon armazon) throws Exception{
        //Paso 1: Preparar la sentencia sql 
        String query = "call eliminarArmazon(?)";
        
        //Paso 2: Conectar a la bd
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3 gENERAR UN OBJETO QUE PERMITA PREPARAR LA LLAMADA AL PROCEDURE 
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4 asignar los valores para cada parametro
        cstmt.setInt(1 , armazon.getIdArmazon());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        
        
        // Paso 8 insertar los valores en el objeto 
        
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
        // Paso 10 devolver o return el id generado
        
    }
    
    //Cambiar estatus a activo 1
     public void estatusActiv(Armazon armazon) throws Exception{
        //Paso 1: Preparar la sentencia sql 
        String query = "call activarArmazon(?)";
        
        //Paso 2: Conectar a la bd
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3 gENERAR UN OBJETO QUE PERMITA PREPARAR LA LLAMADA AL PROCEDURE 
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4 asignar los valores para cada parametro
        cstmt.setInt(1 , armazon.getIdArmazon());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        
        
        // Paso 8 insertar los valores en el objeto 
        
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
        // Paso 10 devolver o return el id generado
        
    }
    
    
}


