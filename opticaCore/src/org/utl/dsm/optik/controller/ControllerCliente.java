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
    /*
    El método insertar recibe un objeto Cliente y utiliza una conexión a una base de 
    datos MySQL para llamar al procedimiento almacenado insertarCliente y agregar el 
    cliente a la base de datos. El método devuelve el ID del nuevo cliente agregado a 
    la base de datos.
    */
    public int insertar(Cliente cliente) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call insertarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int idPersonaG = 0;
        int idClienteG = 0;
        String numeroUnicoG = "";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
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
        
        //Paso 5: Registrar parametros de salida del procedure
        cstmt.registerOutParameter(15, Types.INTEGER);
        cstmt.registerOutParameter(16, Types.INTEGER);
        cstmt.registerOutParameter(17, Types.VARCHAR);
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        idPersonaG = cstmt.getInt(15);
        idClienteG = cstmt.getInt(16);
        numeroUnicoG = cstmt.getString(17);
        
         // Paso 8 insertar los valores en el objeto 
        cliente.getPersona().setIdPersona(idPersonaG);
        cliente.setIdCliente(idClienteG);
        cliente.setNumeroUnico(numeroUnicoG);
        
         // Paso 9 cerrar objetos de conexion
        cstmt.close();
        conn.close();
        conexion.close();

         // Paso 10 devolver o return el id generado
        return idClienteG;
    }
    
    /*
    El método actualizar recibe un objeto Cliente y actualiza los detalles de ese cliente 
    en la base de datos, llamando al procedimiento almacenado actualizarCliente.
    */
    public void actualizar(Cliente cliente) throws Exception{
        
         //Paso 1: Preparar la sentencia sql
        String query = "call actualizarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();     
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
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
        
        //Paso 5: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 6 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();        
    }
    
    /*
    El método eliminar recibe un objeto Cliente y lo elimina de la base de datos 
    utilizando el procedimiento almacenado eliminarCliente.
    */
    public void eliminar(Cliente cliente) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call eliminarCliente(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
         //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, cliente.getIdCliente());
        
        //Paso 5: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 6 cerrar objetos de conexion
        cstmt.close();
        conn.close();
        conexion.close();
    } 
    /*
    El método activar recibe un objeto Cliente y lo activa en la base de datos 
    utilizando el procedimiento almacenado activarCliente.
    */
    
    public void activar(Cliente cliente) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call activarCliente(?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setInt(1, cliente.getIdCliente());
        //Paso 5: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        // Paso 6 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
    }
    /* 
    El método getAll recibe una cadena filtro que se utiliza para filtrar los 
    resultados de la consulta. El método utiliza la consulta SQL 
    SELECT * FROM vistaC WHERE estatus = ? para obtener todos los clientes 
    con el estado especificado. Retorna una lista de objetos Cliente que 
    contienen la información de los clientes recuperados de la base de datos.
    */
    
    public List<Cliente> getAll(String filtro) throws SQLException{
         //Paso 1: Preparar la sentencia sql
        String query = "SELECT * FROM vistaC WHERE estatus="+filtro+";";
        //Paso 2: Conectar a la BD
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
        // Cerramos conexiones
        rs.close();
        pstmt.close();
        conn.close();
        // Devolvemos la lista de clientes
        return clientes;        
    }
}
