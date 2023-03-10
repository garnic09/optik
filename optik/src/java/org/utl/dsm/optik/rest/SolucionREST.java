/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.rest;

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
import org.utl.dsm.optik.controller.*;
import org.utl.dsm.optik.model.*;

@Path("restoptik")
public class SolucionREST extends Application{
    @Path("insertarSolucion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarSolucion(@FormParam ("datosSolucion") @DefaultValue ("") String datosSolucion){
        Gson gson = new Gson();
        Solucion solucion = new Solucion();
        solucion = gson.fromJson(datosSolucion, Solucion.class);
        ControllerSolucion objCS = new ControllerSolucion();
        String out = "";
        try {
            objCS.insertar(solucion);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(solucion);
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    @Path("actualizarSolucion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarSolucion(@FormParam ("datosSolucion") @DefaultValue ("") String datosSolucion){
        Gson gson = new Gson();
        Solucion solucion = new Solucion();
        solucion = gson.fromJson(datosSolucion, Solucion.class);
        ControllerSolucion objCS = new ControllerSolucion();
        String out = "";
        try {
            objCS.actualizar(solucion);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(solucion);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("borrarSolucion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarSolucion(@FormParam ("datosSolucion") @DefaultValue ("") String datosSolucion){
        Gson gson = new Gson();
        Solucion solucion = new Solucion();
        solucion = gson.fromJson(datosSolucion, Solucion.class);
        ControllerSolucion objCS = new ControllerSolucion();
        String out = "";
        try {
            objCS.eliminar(solucion);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(solucion);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("activarSolucion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response activarSolucion(@FormParam ("datosSolucion") @DefaultValue ("") String datosSolucion){
        Gson gson = new Gson();
        Solucion solucion = new Solucion();
        solucion = gson.fromJson(datosSolucion, Solucion.class);
        ControllerSolucion objCS = new ControllerSolucion();
        String out = "";
        try {
            objCS.activar(solucion);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(solucion);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllSolucion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSolucion(@FormParam("estatus") @DefaultValue("1") String estatus){
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerSolucion objCS = new ControllerSolucion();
            List<Solucion> soluciones;
            soluciones = objCS.getAll(estatus);
            out = gson.toJson(soluciones); //Convertimos la lista de empleados a un json
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
