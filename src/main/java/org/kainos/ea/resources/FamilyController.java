package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.FamilyService;
import org.kainos.ea.cli.Family;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 Family API")
@Path("/api")
public class FamilyController {

    private FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GET
    @Path("/families")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamilyByID(int id){
        try{

            return Response.ok(familyService.getFamilyByID(id)).build();

        } catch (FailedToGetFamilyException e) {

            System.err.println(e.getMessage());

            return Response.serverError().build();

        } catch (FamilyDoesNotExistException e) {

            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


}
