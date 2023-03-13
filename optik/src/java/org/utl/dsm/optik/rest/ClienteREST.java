/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.rest;

/**
 *
 * @author garni
 */
import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.controller.ControllerCliente;
import org.utl.dsm.optik.model.Cliente;



@Path("restoptik")
public class ClienteREST extends Application{
    @Path("insertCliente")//Ruta para insertar cliente
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON)//Tipo de respuesta que devuelve
    public Response insertEmpleado(@FormParam ("datosCliente") @DefaultValue ("") String datosCliente){
        Gson gson = new Gson();
        Cliente cliente = new Cliente();
        cliente = gson.fromJson(datosCliente, Cliente.class);
        ControllerCliente objCl = new ControllerCliente();
        String out = "";
        try {
            objCl.insertar(cliente);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(cliente);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    @Path("updateCliente") //Ruta para actualizar cliente
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON) //Tipo de respuesta que devuelve
    public Response updateEmpleado(@FormParam ("datosCliente") @DefaultValue ("") String datosCliente){
        Gson gson = new Gson();
        Cliente cliente = new Cliente();
        cliente = gson.fromJson(datosCliente, Cliente.class);
        ControllerCliente objCl = new ControllerCliente();
        String out = "";
        try {
            objCl.actualizar(cliente);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(cliente);
        return Response.status(Response.Status.OK).entity(out).build();
    }   
    @Path("getAllCliente")//Ruta para obtener todos los clientes
    @POST//Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON)//Tipo de respuesta que devuelve
    public Response getAllCliente(@FormParam("estatus") @DefaultValue("1") String estatus){
        String out = "";
        Gson gson = new Gson();
        try{
            ControllerCliente objCl = new ControllerCliente();
            List<Cliente> clientes;
            clientes = objCl.getAll(estatus);
            out = gson.toJson(clientes);
        }catch(Exception ex){
            out="{\"error\":" + ex.toString()+"}";
             return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        return Response.status(Response.Status.OK).entity(out).build();
        
    }
    @Path("eliminarCliente")//Ruta para eliminar cliente
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON)//Tipo de respuesta que devuelve
    public Response eliminarCliente(@FormParam ("datosCliente") @DefaultValue ("") String datosCliente){
        Gson gson = new Gson();
        Cliente cliente = new Cliente();
        cliente = gson.fromJson(datosCliente, Cliente.class);
        ControllerCliente objCl = new ControllerCliente();
        String out = "";
        try {
            objCl.eliminar(cliente);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(cliente);
        return Response.status(Response.Status.OK).entity(out).build();
    }   
    @Path("activarCliente") //Ruta para activar cliente
    @POST//Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON) //Tipo de respuesta que devuelve
    public Response activarCliente(@FormParam ("datosCliente") @DefaultValue ("") String datosCliente){
        Gson gson = new Gson();
        Cliente cliente = new Cliente();
        cliente = gson.fromJson(datosCliente, Cliente.class);
        ControllerCliente objCl = new ControllerCliente();
        String out = "";
        try {
            objCl.activar(cliente);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(cliente);
        return Response.status(Response.Status.OK).entity(out).build();
    }      
    
    
}