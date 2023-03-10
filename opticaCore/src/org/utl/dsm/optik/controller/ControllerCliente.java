/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

/**
 *
 * @author garni
 */
import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.model.Persona;
import org.utl.dsm.optik.model.Cliente;


public class ControllerCliente {
    public int insertar(Cliente cliente) throws Exception{
        String query = "call insertarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int idPersonaG = 0;
        int idClienteG = 0;
        String numeroUnicoG = "";
        
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        CallableStatement cstmt = conn.prepareCall(query);
        
        cstmt.setString(1, cliente.getPersona().getNombre());
        cstmt.setString(2, cliente.getPersona().getApellidoPaterno());
        cstmt.setString(3, cliente.getPersona().getApellidoMaterno());
        cstmt.setString(4, cliente.getPersona().getGenero());
        cstmt.setString(5, cliente.getPersona().getFechaNacimiento());
        cstmt.setString(6, cliente.getPersona().getCalle());
        cstmt.setString(7, cliente.getPersona().getNumero());
        cstmt.setString(8, cliente.getPersona().getColonia());
        cstmt.setString(9, cliente.getPersona().getCp());
        cstmt.setString(10, cliente.getPersona().getCiudad());
        cstmt.setString(11, cliente.getPersona().getEstado());
        cstmt.setString(12, cliente.getPersona().getTelcasa());
        cstmt.setString(13, cliente.getPersona().getTelmovil());
        cstmt.setString(14, cliente.getPersona().getEmail());   
        
        cstmt.registerOutParameter(15, Types.INTEGER);
        cstmt.registerOutParameter(16, Types.INTEGER);
        cstmt.registerOutParameter(17, Types.VARCHAR);
        
        cstmt.executeUpdate();
        idPersonaG = cstmt.getInt(15);
        idClienteG = cstmt.getInt(16);
        numeroUnicoG = cstmt.getString(17);
        
        cliente.getPersona().setIdPersona(idPersonaG);
        cliente.setIdCliente(idClienteG);
        cliente.setNumeroUnico(numeroUnicoG);
        
        cstmt.close();
        conn.close();
        conexion.close();

        return idClienteG;
    }
    
    public void actualizar(Cliente cliente) throws Exception{
        String query = "call actualizarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();     
        
        CallableStatement cstmt = conn.prepareCall(query);
        
        cstmt.setString(1, cliente.getPersona().getNombre());
        cstmt.setString(2, cliente.getPersona().getApellidoPaterno());
        cstmt.setString(3, cliente.getPersona().getApellidoMaterno());
        cstmt.setString(4, cliente.getPersona().getGenero());
        cstmt.setString(5, cliente.getPersona().getFechaNacimiento());
        cstmt.setString(6, cliente.getPersona().getCalle());
        cstmt.setString(7, cliente.getPersona().getNumero());
        cstmt.setString(8, cliente.getPersona().getColonia());
        cstmt.setString(9, cliente.getPersona().getCp());
        cstmt.setString(10, cliente.getPersona().getCiudad());
        cstmt.setString(11, cliente.getPersona().getEstado());
        cstmt.setString(12, cliente.getPersona().getTelcasa());
        cstmt.setString(13, cliente.getPersona().getTelmovil());
        cstmt.setString(14, cliente.getPersona().getEmail());     
        
        cstmt.setInt(15, cliente.getPersona().getIdPersona());
        cstmt.setInt(16, cliente.getIdCliente());
        
        cstmt.executeUpdate();
        
        cstmt.close();
        conn.close();
        conexion.close();        
    }
    
    public void eliminar(Cliente cliente) throws Exception{
        String query = "call eliminarCliente(?)";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        cstmt.setInt(1, cliente.getIdCliente());
        cstmt.executeUpdate();
        cstmt.close();
        conn.close();
        conexion.close();
    } 
    
    public void activar(Cliente cliente) throws Exception{
        String query = "call activarCliente(?)";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        CallableStatement cstmt = conn.prepareCall(query);
        cstmt.setInt(1, cliente.getIdCliente());
        cstmt.executeUpdate();
        cstmt.close();
        conn.close();
        conexion.close();
    }
    
    public List<Cliente> getAll(String filtro) throws SQLException{
        String query = "SELECT * FROM vistaC WHERE estatus="+filtro+";";
        
        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);
        //Declaramos la lista
        List<Cliente> clientes = new ArrayList<>();
        // nos devuelve la informacion
        ResultSet rs = pstmt.executeQuery();  
        while(rs.next()){
            Persona p = new Persona();
            Cliente c = new Cliente();
            p.setIdPersona(rs.getInt("idPersona"));
            p.setNombre(rs.getString("nombre"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setGenero(rs.getString("genero"));
            p.setFechaNacimiento(rs.getString("fechaNacimiento"));
            p.setCalle(rs.getString("calle"));
            p.setNumero(rs.getString("numero"));
            p.setColonia(rs.getString("colonia"));
            p.setCp(rs.getString("cp"));
            p.setCiudad(rs.getString("ciudad"));
            p.setEstado(rs.getString("estado"));
            p.setTelcasa(rs.getString("telcasa"));
            p.setTelmovil(rs.getString("telmovil"));
            p.setEmail(rs.getString("email"));            
            c.setPersona(p);
            
            c.setIdCliente(rs.getInt("idCliente"));
            c.setNumeroUnico(rs.getString("numeroUnico"));
            c.setEstatus(rs.getInt("estatus"));
            clientes.add(c); 
            
        }
        rs.close();
        pstmt.close();
        conn.close();
        return clientes;        
    }
}
