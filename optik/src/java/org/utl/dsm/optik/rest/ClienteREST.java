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
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.dsm.optik.controller.ControllerCliente;
import org.utl.dsm.optik.model.Cliente;


@Path("restoptik")
public class ClienteREST extends Application{
    @Path("insertCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("updateCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("getAllCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("eliminarCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("activarCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
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
    
    @Path("searchCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCliente(@FormParam("search") @DefaultValue("") String search,
                                    @FormParam("estatus") @DefaultValue("1") String estatus){
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerCliente objCP = new ControllerCliente();
            List<Cliente> clientes;
            clientes = objCP.search(search,estatus);
            out = gson.toJson(clientes); //Convertimos la lista de empleados a un json
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}