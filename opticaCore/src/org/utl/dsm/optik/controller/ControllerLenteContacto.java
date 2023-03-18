/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.model.LenteContacto;
import org.utl.dsm.optik.model.Producto;

/**
 *
 * @author garni
 */
public class ControllerLenteContacto {
    public List<LenteContacto> getAll(String filtro) throws SQLException{
        //Paso 1: Preparar la sentencia sql
        String query = "SELECT * FROM vista_lenteContacto WHERE estatus = " + filtro + ";";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<LenteContacto> lentesContacto = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            LenteContacto a = new LenteContacto();
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
            a.setIdLenteContacto(rs.getInt("ID"));
            a.setKeratometria(rs.getString("Keratometria"));
            a.setFoto(rs.getString("Fotografía"));
            
            lentesContacto.add(a);
        }
        // Cerramos conexiones
        rs.close();
        pstmt.close();
        conn.close();
        
        // Devolvemos la lista de accesorios
        return lentesContacto;
    }
}
