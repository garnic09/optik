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
import org.utl.dsm.optik.controller.ControllerTratamiento;
import org.utl.dsm.optik.model.Tratamiento;

/**
 *
 * @author garni
 */
@Path("restoptik")
public class TratamientoREST extends Application{
    @Path("insertTratamiento")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTratamiento(@FormParam ("datosTratamiento") @DefaultValue ("") String datosTratamiento){
        Gson gson = new Gson();
        Tratamiento tratamiento = new Tratamiento();
        tratamiento = gson.fromJson(datosTratamiento, Tratamiento.class);
        ControllerTratamiento objCT = new ControllerTratamiento();
        String out = "";
        try {
            objCT.insertar(tratamiento);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(tratamiento);
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    @Path("updateTratamiento")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTratamiento(@FormParam ("datosTratamiento") @DefaultValue ("") String datosTratamiento){
        Gson gson = new Gson();
        Tratamiento tratamiento = new Tratamiento();
        tratamiento = gson.fromJson(datosTratamiento, Tratamiento.class);
        ControllerTratamiento objCT = new ControllerTratamiento();
        String out = "";
        try {
            objCT.actualizar(tratamiento);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(tratamiento);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("deleteTratamiento")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTratamiento(@FormParam ("datosTratamiento") @DefaultValue ("") String datosTratamiento){
        Gson gson = new Gson();
        Tratamiento tratamiento = new Tratamiento();
        tratamiento = gson.fromJson(datosTratamiento, Tratamiento.class);
        ControllerTratamiento objCT = new ControllerTratamiento();
        String out = "";
        try {
            objCT.eliminar(tratamiento);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(tratamiento);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("activateTratamiento")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateTratamiento(@FormParam ("datosTratamiento") @DefaultValue ("") String datosTratamiento){
        Gson gson = new Gson();
        Tratamiento tratamiento = new Tratamiento();
        tratamiento = gson.fromJson(datosTratamiento, Tratamiento.class);
        ControllerTratamiento objCT = new ControllerTratamiento();
        String out = "";
        try {
            objCT.activar(tratamiento);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(tratamiento);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllTratamiento")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTratamiento(@FormParam("estatus") @DefaultValue("1") String estatus){
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerTratamiento objCT = new ControllerTratamiento();
            List<Tratamiento> tratamientos;
            tratamientos = objCT.getAll(estatus);
            out = gson.toJson(tratamientos); //Convertimos la lista de empleados a un json
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
