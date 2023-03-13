/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.db;


import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author garni
 */
 
 /*
 Esta clase se encarga de establecer la conexión con una base de datos MySQL 
 y cerrarla. La clase utiliza la librería JDBC para conectarse con la base 
 de datos.
 */
public class ConexionMySQL {
    Connection conn;
    
    /*se encarga de establecer la conexión con la base de datos
    Para ello, se definen tres variables: usuario, password y url, que contienen 
    las credenciales y la URL de la base de datos, respectivamente.
    */
    public Connection open(){
        String usuario = "root";
        String password = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/optiqalumnos?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        try{
        /*carga el driver JDBC de MySQL y se llama al método DriverManager.getConnection() 
        para establecer la conexión. Si todo va bien, el método retorna el objeto 
        Connection.*/
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, password);
            return conn;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    public void close(){
        try{
        //se encarga de cerrar la conexión establecida anteriormente. 
            conn.close();
        } catch (Exception ex){
        //si ocurre alguna excepción durante la conexión o el cierre de la misma, se lanza una excepción 
            throw new RuntimeException(ex);
        }
    }
}
