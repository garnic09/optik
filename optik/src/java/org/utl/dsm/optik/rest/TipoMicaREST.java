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
import org.utl.dsm.optik.controller.ControllerTipoMica;
import org.utl.dsm.optik.model.TipoMica;

/**
 *
 * @author iamra
 */
@Path("restoptik")
public class TipoMicaREST extends Application {

    @Path("getAllMica")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMica() {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerTipoMica objTM = new ControllerTipoMica();
            List<TipoMica> tipoMicas;
            tipoMicas = objTM.getAllMica();
            out = gson.toJson(tipoMicas);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

}
