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
import org.utl.dsm.optik.controller.ControllerMaterial;
import org.utl.dsm.optik.model.Material;

/**
 *
 * @author garni
 */
@Path("restoptik")
public class MaterialREST extends Application{
    @Path("insertMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertMaterial(@FormParam ("datosMaterial") @DefaultValue ("") String datosMaterial){
        Gson gson = new Gson();
        Material material = new Material();
        material = gson.fromJson(datosMaterial, Material.class);
        ControllerMaterial objCM = new ControllerMaterial();
        String out = "";
        try {
            objCM.insertar(material);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(material);
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    @Path("updateMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMaterial(@FormParam ("datosMaterial") @DefaultValue ("") String datosMaterial){
        Gson gson = new Gson();
        Material material = new Material();
        material = gson.fromJson(datosMaterial, Material.class);
        ControllerMaterial objCM = new ControllerMaterial();
        String out = "";
        try {
            objCM.actualizar(material);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(material);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("deleteMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMaterial(@FormParam ("datosMaterial") @DefaultValue ("") String datosMaterial){
        Gson gson = new Gson();
        Material material = new Material();
        material = gson.fromJson(datosMaterial, Material.class);
        ControllerMaterial objCM = new ControllerMaterial();
        String out = "";
        try {
            objCM.eliminar(material);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(material);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("activateMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateMaterial(@FormParam ("datosMaterial") @DefaultValue ("") String datosMaterial){
        Gson gson = new Gson();
        Material material = new Material();
        material = gson.fromJson(datosMaterial, Material.class);
        ControllerMaterial objCM = new ControllerMaterial();
        String out = "";
        try {
            objCM.activar(material);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(material);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMaterial(@FormParam("estatus") @DefaultValue("1") String estatus){
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerMaterial objCM = new ControllerMaterial();
            List<Material> materialaes;
            materialaes = objCM.getAll(estatus);
            out = gson.toJson(materialaes); //Convertimos la lista de materiales a un json
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
