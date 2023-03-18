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
import org.utl.dsm.optik.controller.ControllerVentaLC;
import org.utl.dsm.optik.model.DetalleVentaPreLen;

/**
 *
 * @author garni
 */
@Path("restoptik")
public class VentaLenteREST extends Application {

    @Path("ventaLente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generarVentaLente(@FormParam("datos") @DefaultValue("") String datosVentaLente) {
        Gson gson = new Gson();
        DetalleVentaPreLen dvPreLen = gson.fromJson(datosVentaLente, DetalleVentaPreLen.class);

        ControllerVentaLC cv = new ControllerVentaLC();

        boolean r = cv.generarVentaLentes(dvPreLen);
        String out = "";

        if (r) {
            out = """
                  {"result":"venta lente exitosa"}
                  """;
        } else {
            out = """
                  {"error":"ocurrio un error en la venta del lente"}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

}
