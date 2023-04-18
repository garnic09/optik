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
import org.utl.dsm.optik.controller.ControllerLenteContacto;
import org.utl.dsm.optik.controller.ControllerTickets;
import org.utl.dsm.optik.model.LenteContacto;
import org.utl.dsm.optik.model.TicketLenteC;

/**
 *
 * @author garni
 */
@Path("restoptik")
public class LenteContactoREST extends Application {
    
    @Path("getAllLenteC") //Ruta para obtener todos los accesorios
    @POST //Tipo de peticion
    @Produces(MediaType.APPLICATION_JSON) //Tipo de respuesta que devuelve
    public Response getAllLenteC(@FormParam("estatus") @DefaultValue("1") String estatus){
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerLenteContacto objCLC = new ControllerLenteContacto();
            List<LenteContacto> lentesContacto;
            lentesContacto = objCLC.getAll(estatus);
            out = gson.toJson(lentesContacto); //Convertimos la lista de accesorios a un json
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
