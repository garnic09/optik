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
import org.utl.dsm.optik.controller.ControllerTickets;
import org.utl.dsm.optik.controller.ControllerVentaLC;
import org.utl.dsm.optik.model.DetalleVentaPresupuestoLC;
import org.utl.dsm.optik.model.TicketLenteC;

/**
 *
 * @author garni
 */
@Path("restoptik")
public class VentaLenteContactoREST extends Application{
    @Path("dvlc")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarDVP(@FormParam ("detalleVLC") @DefaultValue ("") String dvlc){
        Gson gson = new Gson();
        DetalleVentaPresupuestoLC detalleVP = new DetalleVentaPresupuestoLC();
        detalleVP = gson.fromJson(dvlc, DetalleVentaPresupuestoLC.class);
        ControllerVentaLC objCA = new ControllerVentaLC();
        String out = "";
        if(objCA.generarVentaLC(detalleVP)){
            out="""
                {"result":"Venta Generada con Exito"}
                """;
        }else {
            out="""
                {"result":"Venta Fallida"}
                """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllTicketLC")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTicketLC(@FormParam("filtro") @DefaultValue("") String filtro, @FormParam("orden") @DefaultValue("desc") String orden) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerTickets objTK = new ControllerTickets();
            List<TicketLenteC> ticketLentesC;
            ticketLentesC = objTK.getAllLC(filtro, orden);
            out = gson.toJson(ticketLentesC);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
