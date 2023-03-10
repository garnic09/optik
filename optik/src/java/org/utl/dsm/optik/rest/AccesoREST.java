/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.core.Response;
import org.utl.dsm.optik.controller.ControllerAcceso;
import org.utl.dsm.optik.model.Empleado;
import org.utl.dsm.optik.model.Usuario;

/**
 *
 * @author garni
 */
//@Path("restoptik")
//public class AccesoREST {

//    @Path("login")
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response acceder(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) {
//        String out = null;
//        Gson gson = new Gson();
//        Usuario usuario = null;
//        Empleado e = null;
//
//        ControllerAcceso objCA = null;
//        try {
//            usuario = gson.fromJson(datosUsuario, Usuario.class);
//            objCA = new ControllerAcceso();
//            e = objCA.acceder(usuario);
//
//            if (e != null) {
//                e.getUsuario().setLastToken();
//                objCA.guardarToken(e);
//                out = gson.toJson(e);
//            } else {
//                out = """
//                      {"error":"Acceso no concedido."}
//                      """;
//            }
//
//        } catch (JsonParseException jpe) {
//            out = """
//                    {"error":"Formato de datos no valido."}
//                  """;
//        } catch (Exception ex) {
//            out = """
//                  {\"error\":"Acceso no concedido"}
//                  """;
//            ex.printStackTrace();
//        }
//        System.out.println(out);
//        return Response.status(Response.Status.OK).entity(out).build();
//    }
//}

@Path("restoptik")
public class AccesoREST {
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceder(@FormParam ("datosUsuario") @DefaultValue ("") String datosUsuario){
        Gson gson = new Gson();
        Usuario usuario = new Usuario();
        Empleado e = new Empleado();
        usuario = gson.fromJson(datosUsuario, Usuario.class);
        System.out.println(usuario.toString());
        ControllerAcceso objCA = new ControllerAcceso();
        try {
            e = objCA.acceder(usuario);
            e.getUsuario().setLastToken();
            objCA.guardarToken(e);
            
        } catch (Exception ex) {
            Logger.getLogger(AccesoREST.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        String out = gson.toJson(e);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response logOut(@FormParam("empleado") @DefaultValue("") String e) throws Exception{
        String out = null;
        Empleado empleado = null;
        Usuario usuario = null;
        ControllerAcceso ca = null;
        Gson gson = new Gson();
        try
        {
            empleado = gson.fromJson(e, Empleado.class);
            ca = new ControllerAcceso();
            if(ca.eliminarToken(empleado)){
                out = """
                      {"ok":"Eliminación de Token correcta"}
                      """;
            }else
                out = """
                      {"error":"Eliminación de Token no realizada"}
                      """;
        } catch(JsonParseException jpe)
        {
            out = """
                  {"error":"Formato de datos no valido"}
                  """;
            jpe.printStackTrace();
        } catch(Exception ex)
        {
            out = """
                  {"error":"Acceso no concedido"}
                  """;
            ex.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}

