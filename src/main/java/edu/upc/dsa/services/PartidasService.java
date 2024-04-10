package edu.upc.dsa.services;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import edu.upc.dsa.PartidasManager;
import edu.upc.dsa.PartidasManagerImpl;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.User;

@Api(value = "/partidas", description = "Endpoint to Partida Service")
@Path("/partidas")
public class PartidasService {
    private PartidasManager pm;
    public PartidasService() {
        this.pm = PartidasManagerImpl.getInstance();

        if (pm.getNumJuegos() == 0) {
            pm.addJuego("LOL", "Juego de lucha", 20);
            pm.addJuego("Celda", "Juego de aventuras", 3);
            pm.addJuego("MB", "Juego de pantallas", 3);

            pm.addUser("11111");
            pm.addUser("22222");
            pm.addUser("33333");

            pm.addPartida("11111", "LOL");
            pm.addPartida("22222", "MB");
            pm.addPartida("333333", "Celda");
        }
    }

    @GET
    @ApiOperation(value = "Consultar puntuación actual", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = String.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("puntuacionActual/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntuacionActual(@PathParam("id") String id) {
        int t = pm.puntuacionActual(id);
        if (t == -1) return Response.status(404).build();
        else  return Response.status(201).entity(String.valueOf(t)).build();
    }
    @GET
    @ApiOperation(value = "Consultar nivel actual", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = String.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("nivelActual/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNivelActual(@PathParam("id") String id) {
        int t = pm.nivelActual(id);
        if (t == -1) return Response.status(404).build();
        else  return Response.status(201).entity(String.valueOf(t)).build();
    }

    @GET
    @ApiOperation(value = "list the users of a game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List")
    })
    @Path("usersByJuego/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByJuego(@PathParam("id") String id) {
        List<User> users = this.pm.usersByJuego(id);
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users){};
        return Response.status(201).entity(entity).build();
    }

}
