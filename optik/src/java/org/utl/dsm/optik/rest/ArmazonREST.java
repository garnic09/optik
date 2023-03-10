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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.controller.ControllerAcceso;
import org.utl.dsm.optik.controller.ControllerArmazon;
import org.utl.dsm.optik.model.Armazon;

/**
 *
 * @author ferna
 */
@Path("restoptik")
public class ArmazonREST extends Application {

    @Path("insertArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertArmazon(@FormParam("datosArmazon") @DefaultValue("") String datosArmazon,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }

            Gson gson = new Gson();
            Armazon armazon = new Armazon();
            armazon = gson.fromJson(datosArmazon, Armazon.class);
            ControllerArmazon objAr = new ControllerArmazon();
            String out = "";
            try {
                objAr.insertar(armazon);
            } catch (Exception ex) {
                out = "{\"error\":" + ex.toString() + "}";
            }
            out = gson.toJson(armazon);
            return Response.status(Response.Status.OK).entity(out).build();

        } catch (Exception ex) {
            Logger.getLogger(ArmazonREST.class.getName()).log(Level.SEVERE, null, ex);
            String out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
    }

    @Path("actualizarArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArmazon(@FormParam("datosArmazon") @DefaultValue("") String datosArmazon,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            Gson gson = new Gson();
            Armazon armazon = new Armazon();
            armazon = gson.fromJson(datosArmazon, Armazon.class);
            ControllerArmazon objCA = new ControllerArmazon();
            String out = "";
            try {
                objCA.actualizar(armazon);
            } catch (Exception ex) {
                out = "{\"error\":" + ex.toString() + "}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            out = gson.toJson(armazon);
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            Logger.getLogger(ArmazonREST.class.getName()).log(Level.SEVERE, null, ex);
            String out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
    }

    @Path("getAllArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArmazon(@FormParam("estatus") @DefaultValue("1") String estatus,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            String out = "";
            Gson gson = new Gson();
            try {
                ControllerArmazon objCE = new ControllerArmazon();
                List<Armazon> armazon;
                armazon = objCE.getAll(estatus);
                out = gson.toJson(armazon);
            } catch (Exception ex) {
                out = "{\"error\":\"" + ex.toString() + "\"}";
            }
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            Logger.getLogger(ArmazonREST.class.getName()).log(Level.SEVERE, null, ex);
            String out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
    }

    @Path("estatusInactivoArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response estatusInactivo(@FormParam("datosArmazon") @DefaultValue("") String datosArmazon,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            Gson gson = new Gson();
            Armazon armazon = new Armazon();
            armazon = gson.fromJson(datosArmazon, Armazon.class);
            ControllerArmazon objCE = new ControllerArmazon();

            String out = "";
            try {
                objCE.estatusInactivo(armazon);
                out = gson.toJson(armazon);
            } catch (Exception ex) {
                out = "{\"error\":\"" + ex.toString() + "\"}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            out = gson.toJson(armazon);
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            Logger.getLogger(ArmazonREST.class.getName()).log(Level.SEVERE, null, ex);
            String out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
    }

    @Path("estatusActivoArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response estatusActivo(@FormParam("datosArmazon") @DefaultValue("") String datosArmazon,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            Gson gson = new Gson();
            Armazon armazon = new Armazon();
            armazon = gson.fromJson(datosArmazon, Armazon.class);
            ControllerArmazon objCE = new ControllerArmazon();
            String out = "";
            try {
                objCE.estatusActiv(armazon);
            } catch (Exception ex) {
                out = "{\"error\":\"" + ex.toString() + "\"}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            out = gson.toJson(armazon);
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            Logger.getLogger(ArmazonREST.class.getName()).log(Level.SEVERE, null, ex);
            String out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
    }
}
