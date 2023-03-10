
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
import org.utl.dsm.optik.model.Producto;
import org.utl.dsm.optik.model.Solucion;

public class ControllerSolucion {
    public int insertar(Solucion solucion) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call insertarSoluciones(?,?,?,?,?,?,?,?)";
        int idProductoG = 0;
        int idSolucionG = 0;
        //String codigoBarrasG = "";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, solucion.getProducto().getCodigoBarras());
        cstmt.setString(2, solucion.getProducto().getNombre());
        cstmt.setString(3, solucion.getProducto().getMarca());
        cstmt.setDouble(4, solucion.getProducto().getPrecioCompra());
        cstmt.setDouble(5, solucion.getProducto().getPrecioVenta());
        cstmt.setInt(6, solucion.getProducto().getExistencias());
        cstmt.setInt(7, solucion.getProducto().getEstatus());
        
        //Paso 5: Registrar parametros de salida del procedure
        cstmt.registerOutParameter(8, Types.INTEGER);
        
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        idProductoG = cstmt.getInt(8);
       
        
        // Paso 8 insertar los valores en el objeto 
        solucion.getProducto().setIdProducto(idProductoG);
        
        //accesorio.getProducto().setCodigoBarras(codigoBarrasG);
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
        // Paso 10 devolver o return el id generado
        return idSolucionG;
    }
    
    public void actualizar(Solucion solucion) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call actualizarSolucion(?,?,?,?,?,?,?,?,?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, solucion.getProducto().getCodigoBarras());
        cstmt.setString(2, solucion.getProducto().getNombre());
        cstmt.setString(3, solucion.getProducto().getMarca());
        cstmt.setDouble(4, solucion.getProducto().getPrecioCompra());
        cstmt.setDouble(5, solucion.getProducto().getPrecioVenta());
        cstmt.setInt(6, solucion.getProducto().getExistencias());
        cstmt.setInt(7, solucion.getProducto().getEstatus());
        cstmt.setInt(8, solucion.getProducto().getIdProducto());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
    }
    
    public void eliminar(Solucion solucion) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "CALL eliminarSolucion(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, solucion.getProducto().getIdProducto());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
    }
    
    public void activar(Solucion solucion) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call activarSolucion(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
       cstmt.setInt(1, solucion.getProducto().getIdProducto());
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
    }
    
    public List<Solucion> getAll(String filtro) throws SQLException{
        //Paso 1: Preparar la sentencia sql
        String query = "SELECT * FROM vista_solucion WHERE estatus = " + filtro + ";";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Solucion> solucion = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Solucion s = new Solucion();
            Producto p = new Producto();
            p.setIdProducto(rs.getInt("IDP"));
            p.setCodigoBarras(rs.getString("Codigo_Barras"));
            p.setEstatus(rs.getInt("Estatus"));
            p.setExistencias(rs.getInt("Existencias"));
            p.setMarca(rs.getString("Marca"));
            p.setNombre(rs.getString("Nombre"));
            p.setPrecioCompra(rs.getDouble("Precio_Compra"));
            p.setPrecioVenta(rs.getDouble("Precio_Venta"));
            s.setProducto(p);
            s.setIdSolucion(rs.getInt("ID"));
            
            solucion.add(s);
        }
        rs.close();
        pstmt.close();
        conn.close();
        
        return solucion;
    }
}
