/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import org.utl.dsm.optik.model.DetalleVentaP;

import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author garni
 */
public class ControllerVentaP {

    public boolean generarVentaP(DetalleVentaP dvp) {
        boolean r = false;
        ConexionMySQL conMySQL = new ConexionMySQL();
        Connection conn = conMySQL.open();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String query1 = "INSERT INTO venta (idEmpleado, clave) VALUES(" + dvp.getVenta().getEmpleado().getIdEmpleado() + ",'" + dvp.getVenta().getClave() + "');";
            stmt.execute(query1);
            rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");

            if (rs.next()) {
                dvp.getVenta().setIdVenta(rs.getInt(1));
            }

            for (int i = 0; i < dvp.getListaVP().size(); i++) {
                String query2 = "INSERT INTO venta_producto VALUES(" + dvp.getVenta().getIdVenta() + "," + dvp.getListaVP().get(i).getProducto().getIdProducto() + "," + dvp.getListaVP().get(i).getCantidad() + "," + dvp.getListaVP().get(i).getPrecioUnitario() + "," + dvp.getListaVP().get(i).getDescuento() + ");";
                stmt.execute(query2);
            }
            conn.commit();
            conn.setAutoCommit(true);

            rs.close();
            stmt.close();
            conn.close();
            conMySQL.close();
            r = true;
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentaP.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                conn.close();
                conMySQL.close();
                r = false;
            } catch (SQLException ex1) {
                Logger.getLogger(ControllerVentaP.class.getName()).log(Level.SEVERE, null, ex1);
            }
            r = false;
        }
        return r;
    }
}
