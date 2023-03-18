/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.model.DetalleVentaPresupuestoLC;
import org.utl.dsm.optik.model.Presupuesto;

/**
 *
 * @author garni
 */
public class ControllerVentaLenteContacto {
    public List<Presupuesto> agregarPresupuesto(DetalleVentaPresupuestoLC dvp){
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        Statement stmnt = null;
        ResultSet rs = null;
        List<Presupuesto> p = new ArrayList<>();
        Presupuesto ps = null;
        try {
            conn.setAutoCommit(false);
            
        } catch (SQLException ex) {
            
        }
        return p;
    }
}
