/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;
import java.util.List;
import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.model.TipoMica;
/**
 *
 * @author garni
 */
public class ControllerTipoMica {
    public List<TipoMica> getAllMica() throws SQLException {
        String query = "SELECT * from tipo_mica where idTipoMica = 1;";

        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);

        List<TipoMica> tipoMicas = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            TipoMica tm = new TipoMica();
            tm.setIdTipoMica(rs.getInt("idTipoMica"));
            tm.setNombre(rs.getString("nombre"));
            tm.setPrecioCompra(rs.getFloat("precioCompra"));
            tm.setPrecioVenta(rs.getFloat("precioVenta"));
            tipoMicas.add(tm);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return tipoMicas;
    }
}
