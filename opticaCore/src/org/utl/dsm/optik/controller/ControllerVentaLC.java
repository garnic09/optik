/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.model.DetalleVentaPresupuestoLC;
import org.utl.dsm.optik.model.DetalleVentaPreLen;

/**
 *
 * @author garni
 */
public class ControllerVentaLC {

    public boolean generarVentaLC(DetalleVentaPresupuestoLC dvp) {
        boolean r = false;
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        Statement stmnt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);
            stmnt = conn.createStatement();
            // Se inserta la venta
            String query1 = "INSERT INTO venta (idEmpleado, clave) "
                    + "VALUES (" + dvp.getVenta().getEmpleado().getIdEmpleado()
                    + ',' + dvp.getVenta().getClave() + ");";
            stmnt.execute(query1);
            // Se obtiene el id de la venta que se ha insertado
            String query2 = "SELECT LAST_INSERT_ID()";
            rs = stmnt.executeQuery(query2);
            if (rs.next()) {
                dvp.getVenta().setIdVenta(rs.getInt(1));
            }

            for (int i = 0; i < dvp.getListVentaPresupuestoLC().size(); i++) {
                //Se inserta el presupuesto
                String query3 = "INSERT INTO presupuesto"
                        + "(idExamenVista, clave)"
                        + "VALUES (" + dvp.getListVentaPresupuestoLC().get(i).getPresupuestoVentaLC().getPresupuesto().getExamenVista().getIdExamenVista()
                        + "," + dvp.getListVentaPresupuestoLC().get(i).getPresupuestoVentaLC().getPresupuesto().getClave() + " );";
                stmnt.execute(query3);
                //Se obtiene el id del presupuesto generado
                rs = stmnt.executeQuery(query2);
                if (rs.next()) {
                    dvp.getListVentaPresupuestoLC().get(i).getPresupuestoVentaLC().getPresupuesto().setIdPresupuesto(rs.getInt(1));
                }

                //Se inserta en presupuesto_lentescontacto                
                String query4 = "INSERT INTO presupuesto_lentescontacto"
                        + "(idExamenVista, idLenteContacto, clave)"
                        + "VALUES (" + dvp.getListVentaPresupuestoLC().get(i).getPresupuestoVentaLC().getPresupuesto().getExamenVista().getIdExamenVista() + ","
                        + dvp.getListVentaPresupuestoLC().get(i).getPresupuestoVentaLC().getLenteContacto().getIdLenteContacto() + ","
                        + dvp.getListVentaPresupuestoLC().get(i).getPresupuestoVentaLC().getClave() + ");";
                stmnt.execute(query4);
                //Se obtiene el id del presupuesto_lentescontacto generado
                rs = stmnt.executeQuery(query2);
                if (rs.next()) {
                    dvp.getListVentaPresupuestoLC().get(i).getPresupuestoVentaLC().setIdPresupuestoLentesContacto(rs.getInt(1));
                }

                //Se insera en venta_presupuesto la relación entre la venta y el presupuesto
                String query5 = "INSERT INTO venta_presupuesto "
                        + "(idVenta, idPresupuesto, cantidad, precioUnitario, descuento) "
                        + "VALUES ("
                        + dvp.getVenta().getIdVenta() + ","
                        + dvp.getListVentaPresupuestoLC().get(i).getPresupuestoVentaLC().getPresupuesto().getIdPresupuesto() + ","
                        + dvp.getListVentaPresupuestoLC().get(i).getCantidad() + ","
                        + dvp.getListVentaPresupuestoLC().get(i).getPrecioUnitario() + ","
                        + dvp.getListVentaPresupuestoLC().get(i).getDescuento() + ");";
                stmnt.execute(query5);
            }
            conn.commit();
            conn.setAutoCommit(true);
            stmnt.close();
            conn.close();
            r = true;

        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentaLC.class.getName()).log(Level.SEVERE, null, ex);
            try {

                conn.rollback();
                conn.setAutoCommit(true);
                conn.close();
                r = false;
            } catch (SQLException ex1) {
                r = false;
            }
        }

        return r;
    }

    public boolean generarVentaLentes(DetalleVentaPreLen dvp) {
        boolean r = false;

        //Creamos un objeto conexión con MySQL
        ConexionMySQL connMySQL = new ConexionMySQL();
        //objeto de tipo Connection que abre la conexión
        Connection conn = connMySQL.open();
        //objeto de Statement
        Statement stmt = null;
        //objeto de ResultSet
        ResultSet rs = null;
        /*NOTA: Los objetos quedan afuera del try catch, para poder utilizarlos en todo el método
         */
        try {
            //el autocommit se prepara para no ejecutar en automatico las sentencias
            //sino esperar a que termine la transaccion 
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            //esta query se utiliza varias veces
            String query = "SELECT LAST_INSERT_ID()";

            //Se genera la venta
            String query0 = "INSERT INTO venta (idEmpleado, clave) "
                    + "VALUES (" + dvp.getVenta().getEmpleado().getIdEmpleado()
                    + ",'" + dvp.getVenta().getClave() + "');";
            stmt.execute(query0);
            //Se obtiene el id genrado con la insercion de venta
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                dvp.getVenta().setIdVenta(rs.getInt(1));
            }

            //Se insertan varios presupuestos, por lo tanto se Cicla
            for (int i = 0; i < dvp.getListaVentaPresupuestoL().size(); i++) {

                //Se insertan los presupuestos
                String query1 = "INSERT INTO presupuesto(idExamenVista, clave)"
                        + "VALUES (" + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().getExamenVista().getIdExamenVista() + ","
                        + "'" + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().getClave() + "'" + ");";

                stmt.execute(query1);
                rs = stmt.executeQuery(query);
                //Obtenemos el id de presupuesto y lo guardamos
                if (rs.next()) {
                    dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().setIdPresupuesto(rs.getInt(1));
                }

                //Se insertan los presupuestos de lentes
                String query2 = "INSERT INTO presupuesto_Lentes(idPresupuesto, alturaOblea, idTipoMica, idMaterial, idArmazon)"
                        + "VALUES (" + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().getIdPresupuesto() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getAlturaOblea() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getTipoMica().getIdTipoMica() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getMaterial().getIdMaterial() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getArmazon().getIdArmazon() + ");";

                stmt.execute(query2);
                //Obtenermos el id de presupuesto_Lentes  y se guarda en su objeto
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().setIdPresupuestoLentes(rs.getInt(1));
                }

                //Se almacenana los tratamientos que tiene ese lente presupuestado
                //Va en un ciclo por que se tiene la posibilidad de elegir varios tratamientos
                for (int j = 0; j < dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getListTratamientos().size(); j++) {
                    String query3 = "INSERT INTO presupuesto_lentes_tratamientos VALUES("
                            + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getIdPresupuestoLentes() + ","
                            + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getListTratamientos().get(j).getIdTratamiento() + ");";
                    stmt.execute(query3);

                }
                //Query numero 4 para almacenar la relacion de venta_presupuesto
                String query4 = "INSERT INTO venta_presupuesto VALUES("
                        + dvp.getVenta().getIdVenta() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().getIdPresupuesto() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getCantidad() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPrecioUnitario() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getDescuento() + ");";
                stmt.execute(query4);
            }

            //Ya con todas las sentencias ejecutadas, se envia la conformación de ejecutar la transaccion
            conn.commit();
            //Se cierran los objetos de BD
            conn.setAutoCommit(true);
            rs.close();
            stmt.close();
            r = true;

        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentaLC.class.getName()).log(Level.SEVERE, null, ex);
            try {
                //En caso de error se indica un rollback a la transaccion. 
                conn.rollback();
                conn.setAutoCommit(true);
                r = false;

            } catch (SQLException ex1) {
                Logger.getLogger(ControllerVentaLC.class.getName()).log(Level.SEVERE, null, ex);
                r = false;
            }
        }
        return r;
    }
}
