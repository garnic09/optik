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

    // Este código define un método POST en una API REST para insertar una nueva instancia de la clase Armazon en la base de datos.
    @Path("insertArmazon") // Define la ruta para acceder al método.
    @POST // Especifica que este método maneja solicitudes POST.
    @Produces(MediaType.APPLICATION_JSON) // Especifica el tipo de contenido de la respuesta que será devuelta.

    public Response insertArmazon(@FormParam("datosArmazon") @DefaultValue("") String datosArmazon,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
// Valida el token de acceso para determinar si el usuario tiene permiso para realizar la operación.
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }

            Gson gson = new Gson();
            Armazon armazon = new Armazon();
            armazon = gson.fromJson(datosArmazon, Armazon.class); // Convierte los datos enviados a una instancia de Armazon.
            ControllerArmazon objAr = new ControllerArmazon();
            String out = "";
            try {
                objAr.insertar(armazon); // Inserta la nueva instancia de Armazon en la base de datos.
            } catch (Exception ex) {
                out = "{\"error\":" + ex.toString() + "}";
            }
            out = gson.toJson(armazon); // Convierte la instancia de Armazon en formato JSON.
            return Response.status(Response.Status.OK).entity(out).build(); // Devuelve una respuesta exitosa con el objeto JSON creado.

        } catch (Exception ex) {
            Logger.getLogger(ArmazonREST.class.getName()).log(Level.SEVERE, null, ex);
            String out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build(); // Devuelve un error de solicitud.
        }
    }

    @Path("actualizarArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArmazon(@FormParam("datosArmazon") @DefaultValue("") String datosArmazon,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            // Se valida el token enviado mediante el objeto ControllerAcceso.
            if (!new ControllerAcceso().validarToken(lastToken)) {
                // Si el token no es válido, se devuelve un mensaje de error en formato JSON.
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }

            // Se utiliza la biblioteca Gson para convertir la cadena de datosArmazon en un objeto Armazon.
            Gson gson = new Gson();
            Armazon armazon = gson.fromJson(datosArmazon, Armazon.class);

            // Se crea un objeto ControllerArmazon para actualizar el objeto Armazon.
            ControllerArmazon objCA = new ControllerArmazon();
            String out = "";

            try {
                // Se actualiza el objeto Armazon en la base de datos.
                objCA.actualizar(armazon);
            } catch (Exception ex) {
                // Si se produce una excepción, se devuelve un mensaje de error en formato JSON.
                out = "{\"error\":" + ex.toString() + "}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }

            // Si la actualización se realizó correctamente, se convierte el objeto Armazon actualizado en una cadena JSON y se devuelve en la respuesta.
            out = gson.toJson(armazon);
            return Response.status(Response.Status.OK).entity(out).build();

        } catch (Exception ex) {
            // Si se produce una excepción, se registra en el registro de errores y se devuelve un mensaje de error en formato JSON.
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
            // Verifica si el token proporcionado es válido
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            String out = "";
            Gson gson = new Gson();
            try {
                // Obtiene todos los registros de la tabla Armazon con el estatus especificado
                ControllerArmazon objCE = new ControllerArmazon();
                List<Armazon> armazon;
                armazon = objCE.getAll(estatus);
                // Convierte la lista a formato JSON
                out = gson.toJson(armazon);
            } catch (Exception ex) {
                out = "{\"error\":\"" + ex.toString() + "\"}";
            }
            // Devuelve los registros en formato JSON
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
            // Se valida el token enviado en la petición
            if (!new ControllerAcceso().validarToken(lastToken)) {
                // Si el token no es válido se retorna un error
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            // Se crea una instancia de la clase Gson para convertir el objeto JSON a objeto Java
            Gson gson = new Gson();
            Armazon armazon = new Armazon();
            // Se convierte el objeto JSON enviado en la petición a un objeto Java
            armazon = gson.fromJson(datosArmazon, Armazon.class);
            ControllerArmazon objCE = new ControllerArmazon();

            String out = "";
            try {
                // Se cambia el estatus del objeto Armazon a inactivo
                objCE.estatusInactivo(armazon);
                out = gson.toJson(armazon);
            } catch (Exception ex) {
                // Si se produce un error se retorna un error
                out = "{\"error\":\"" + ex.toString() + "\"}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            // Se convierte el objeto Java a un objeto JSON
            out = gson.toJson(armazon);
            // Se retorna la respuesta con el objeto Armazon en formato JSON y código 200 OK
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            // Si se produce un error se retorna un error
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
            // Verificar el token de acceso
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            // Convertir el objeto JSON de entrada a un objeto Armazon
            Gson gson = new Gson();
            Armazon armazon = new Armazon();
            armazon = gson.fromJson(datosArmazon, Armazon.class);
            // Llamar al controlador para actualizar el estado a activo
            ControllerArmazon objCE = new ControllerArmazon();
            String out = "";
            try {
                objCE.estatusActiv(armazon);
            } catch (Exception ex) {
                // Si ocurre algún error, se envía una respuesta con un mensaje de error
                out = "{\"error\":\"" + ex.toString() + "\"}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
            // Convertir el objeto Armazon actualizado a JSON y enviar una respuesta OK
            out = gson.toJson(armazon);
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            // Si ocurre algún error, se registra en el log y se envía una respuesta con un mensaje de error
            Logger.getLogger(ArmazonREST.class.getName()).log(Level.SEVERE, null, ex);
            String out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }

    }
}
