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
import org.utl.dsm.optik.controller.ControllerAccesorio;
import org.utl.dsm.optik.model.Accesorio;

/**
 *
 * @author garni
 */
@Path("restoptik")
public class AccesorioREST extends Application{
    @Path("insertAccesorio") //Ruta para insertar accesorio
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON) //Tipo de respuesta que devuelve
    public Response insertAccesorio(@FormParam ("datosAccesorio") @DefaultValue ("") String datosAccesorio){
        Gson gson = new Gson();
        Accesorio accesorio = new Accesorio();
        accesorio = gson.fromJson(datosAccesorio, Accesorio.class);
        ControllerAccesorio objCA = new ControllerAccesorio();
        String out = "";
        try {
            objCA.insertar(accesorio);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(accesorio);
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    @Path("updateAccesorio") //Ruta para actualizar accesorio
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON) //Tipo de respuesta que devuelve
    public Response updateAccesorio(@FormParam ("datosAccesorio") @DefaultValue ("") String datosAccesorio){
        Gson gson = new Gson();
        Accesorio accesorio = new Accesorio();
        accesorio = gson.fromJson(datosAccesorio, Accesorio.class);
        ControllerAccesorio objCA = new ControllerAccesorio();
        String out = "";
        try {
            objCA.actualizar(accesorio);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(accesorio);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("deleteAccesorio") //Ruta para eliminar accesorio
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON) //Tipo de respuesta que devuelve
    public Response deleteAccesorio(@FormParam ("datosAccesorio") @DefaultValue ("") String datosAccesorio){
        Gson gson = new Gson();
        Accesorio accesorio = new Accesorio();
        accesorio = gson.fromJson(datosAccesorio, Accesorio.class);
        ControllerAccesorio objCA = new ControllerAccesorio();
        String out = "";
        try {
            objCA.eliminar(accesorio);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(accesorio);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("activateAccesorio") //Ruta para activar accesorio
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON) //Tipo de respuesta que devuelve
    public Response activateAccesorio(@FormParam ("datosAccesorio") @DefaultValue ("") String datosAccesorio){
        Gson gson = new Gson();
        Accesorio accesorio = new Accesorio();
        accesorio = gson.fromJson(datosAccesorio, Accesorio.class);
        ControllerAccesorio objCA = new ControllerAccesorio();
        String out = "";
        try {
            objCA.activar(accesorio);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(accesorio);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllAccesorio") //Ruta para obtener todos los accesorios
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON) //Tipo de respuesta que devuelve
    public Response getAllAccesorio(@FormParam("estatus") @DefaultValue("1") String estatus){
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerAccesorio objCA = new ControllerAccesorio();
            List<Accesorio> accesorios;
            accesorios = objCA.getAll(estatus);
            out = gson.toJson(accesorios); //Convertimos la lista de accesorios a un json
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
