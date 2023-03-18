/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;
import org.utl.dsm.optik.model.ExamenVista;
import org.utl.dsm.optik.model.Empleado;
import org.utl.dsm.optik.model.Cliente;
import org.utl.dsm.optik.model.GraduacionLentes;
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

/**
 *
 * @author garni
 */
public class ControllerExamenVista {

    public List<ExamenVista> getAllExamen(int id) throws SQLException {
        String query = "SELECT * FROM examen_vista WHERE idCliente ='" + id + "';";

        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<ExamenVista> examenVistas = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            ExamenVista ex = new ExamenVista();
            Empleado e = new Empleado();
            Cliente c = new Cliente();
            GraduacionLentes g = new GraduacionLentes();
            ex.setIdExamenVista(rs.getInt("idExamenVista"));
            c.setIdCliente(rs.getInt("idCliente"));
            e.setIdEmpleado(rs.getInt("idEmpleado"));
            g.setIdGraduacion(rs.getInt("idGraduacion"));
            ex.setClave(rs.getString("clave"));
            ex.setCliente(c);
            ex.setEmpleado(e);
            ex.setGraduacion(g);

            // Obtener la fecha como un objeto Timestamp
            Timestamp timestamp = rs.getTimestamp("fecha");
            // Convertir el objeto Timestamp a un objeto Date
            Date date = new Date(timestamp.getTime());
            // Crear un objeto SimpleDateFormat con el patrón "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Formatear la fecha Date con el objeto SimpleDateFormat y obtener el String resultante
            String fechaFormateada = dateFormat.format(date);
            // Establecer la fecha formateada en el objeto ExamenVista
            ex.setFecha(fechaFormateada);

            examenVistas.add(ex);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return examenVistas;
    }
}
