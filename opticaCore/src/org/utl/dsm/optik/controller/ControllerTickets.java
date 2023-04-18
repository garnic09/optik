package org.utl.dsm.optik.controller;

import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.model.TicketProducto;
import org.utl.dsm.optik.model.TicketLente;
import org.utl.dsm.optik.model.TicketLenteC;

/**
 *
 * @author iamra
 */
public class ControllerTickets {

    public List<TicketProducto> getAllP(String filtro, String orden) throws SQLException {
        String query = "SELECT * from vista_ventaproductos WHERE empleado = '" + filtro + "' or apellidoPaterno = '" + filtro + "' or apellidoMaterno = '" + filtro + "' or clave = '" + filtro + "' order by totalClave " + orden + ";";

        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);

        List<TicketProducto> ticketProductos = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            TicketProducto tkp = new TicketProducto();
            tkp.setEmpleado(rs.getString("Empleado"));
            tkp.setApellidoPaterno(rs.getString("apellidoPaterno"));
            tkp.setApellidoMaterno(rs.getString("apellidoMaterno"));
            tkp.setNumeroUnico(rs.getString("numeroUnico"));
            tkp.setCodigoBarras(rs.getString("codigoBarras"));
            tkp.setNombre(rs.getString("nombre"));
            tkp.setMarca(rs.getString("marca"));
            tkp.setClave(rs.getString("clave"));
            tkp.setCantidad(rs.getInt("cantidad"));
            tkp.setPrecioUnitario(rs.getDouble("precioUnitario"));
            tkp.setDescuento(rs.getDouble("descuento"));
            ticketProductos.add(tkp);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return ticketProductos;
    }

    public List<TicketLente> getAllL(String filtro, String orden) throws SQLException {
        String query = "SELECT * from vista_ventalente WHERE nombre = '" + filtro + "' or apellidoPaterno = '" + filtro + "' or apellidoMaterno = '" + filtro + "' or claveV = '" + filtro + "' or nombrec = '" + filtro + "' or apellidoPaternoC = '" + filtro + "' or apellidoMaternoC = '" + filtro + "' or nombreM = '" + filtro + "' or nombreA = '" + filtro + "' or marca = '" + filtro + "' or claveEV = '" + filtro + "' order by totalClave " + orden + ";";
        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);

        List<TicketLente> ticketLentes = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            TicketLente tkl = new TicketLente();
            //empleado
            tkl.setNombreE(rs.getString("nombre"));
            tkl.setApellidoPaternoE(rs.getString("apellidoPaterno"));
            tkl.setApellidoMaternoE(rs.getString("apellidoMaterno"));
            tkl.setNumeroUnicoE(rs.getString("numeroUnicoE"));

            //Cliente 
            tkl.setNombreC(rs.getString("nombrec"));
            tkl.setApellidoPaternoC(rs.getString("apellidoPaternoC"));
            tkl.setApellidoMaternoC(rs.getString("apellidoMaternoC"));
            tkl.setNumeroUnicoC(rs.getString("numeroUnicoC"));

            //Venta
            tkl.setClaveV(rs.getString("claveV"));

            //Material
            tkl.setNombreM(rs.getString("nombreM"));

            //Armazon
            tkl.setNombreA(rs.getString("nombreA"));
            tkl.setMarca(rs.getString("marca"));

            //Tipo Mica
            tkl.setNombreTP(rs.getString("nombreTP"));

            //Examen Vista
            tkl.setClaveEV(rs.getString("claveEV"));
            // Obtener la fecha como un objeto Timestamp
            Timestamp timestamp = rs.getTimestamp("fecha");
            // Convertir el objeto Timestamp a un objeto Date
            Date date = new Date(timestamp.getTime());
            // Crear un objeto SimpleDateFormat con el patrón "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Formatear la fecha Date con el objeto SimpleDateFormat y obtener el String resultante
            String fechaFormateada = dateFormat.format(date);
            // Establecer la fecha formateada en el objeto Ticket venta de lentes
            tkl.setFecha(fechaFormateada);

            //Tratamiento
//            tkl.setNombreT(rs.getString("nombreT"));
            //Presupuesto Lentes
            tkl.setCantidad(rs.getInt("cantidadVP"));
            tkl.setPrecioUnitario(rs.getDouble("precioUnitarioVP"));
            tkl.setDescuento(rs.getDouble("descuentoVP"));

            ticketLentes.add(tkl);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return ticketLentes;
    }

    public List<TicketLenteC> getAllLC(String filtro, String orden) throws SQLException {
        String query = "SELECT * from vista_ventalenteC WHERE nombre = '" + filtro + "' or apellidoPaterno = '" + filtro + "' or apellidoMaterno = '" + filtro + "' or claveV = '" + filtro + "' or nombrec = '" + filtro + "' or apellidoPaternoC = '" + filtro + "' or apellidoMaternoC = '" + filtro + "' or marca = '" + filtro + "' or claveEV = '" + filtro + "' order by totalClave  " + orden + ";";
        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);

        List<TicketLenteC> ticketLentesC = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            TicketLenteC tkl = new TicketLenteC();
            //empleado
            tkl.setNombreE(rs.getString("nombre"));
            tkl.setApellidoPaternoE(rs.getString("apellidoPaterno"));
            tkl.setApellidoMaternoE(rs.getString("apellidoMaterno"));
            tkl.setNumeroUnicoE(rs.getString("numeroUnicoE"));

            //Cliente 
            tkl.setNombreC(rs.getString("nombrec"));
            tkl.setApellidoPaternoC(rs.getString("apellidoPaternoC"));
            tkl.setApellidoMaternoC(rs.getString("apellidoMaternoC"));
            tkl.setNumeroUnicoC(rs.getString("numeroUnicoC"));

            //Venta
            tkl.setClaveV(rs.getString("claveV"));

            //Examen Vista
            tkl.setClaveEV(rs.getString("claveEV"));
            // Obtener la fecha como un objeto Timestamp
            Timestamp timestamp = rs.getTimestamp("fecha");
            // Convertir el objeto Timestamp a un objeto Date
            Date date = new Date(timestamp.getTime());
            // Crear un objeto SimpleDateFormat con el patrón "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Formatear la fecha Date con el objeto SimpleDateFormat y obtener el String resultante
            String fechaFormateada = dateFormat.format(date);
            // Establecer la fecha formateada en el objeto Ticket venta de lentes
            tkl.setFecha(fechaFormateada);

            //Presupuesto Lentes
            tkl.setCantidad(rs.getInt("cantidadVP"));
            tkl.setPrecioUnitario(rs.getDouble("precioUnitarioVP"));
            tkl.setDescuento(rs.getDouble("descuentoVP"));

            //Graduacion
            tkl.setEsferaod(rs.getDouble("esferaod"));
            tkl.setEsferaoi(rs.getDouble("esferaoi"));
            tkl.setCilindrood(rs.getInt("cilindrood"));
            tkl.setCilindrooi(rs.getInt("cilindrooi"));
            tkl.setEjeod(rs.getInt("ejeod"));
            tkl.setEjeoi(rs.getInt("ejeoi"));
            tkl.setDip(rs.getString("dip"));

            //Lente Contacto
            tkl.setKeratometria(rs.getInt("keratometria"));
            tkl.setNombreP(rs.getString("nombreP"));
            tkl.setMarca(rs.getString("marca"));

            ticketLentesC.add(tkl);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return ticketLentesC;
    }
}
