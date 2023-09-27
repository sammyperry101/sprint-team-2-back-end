package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.FamilyService;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 Family API")
@Path("/api")
public class FamilyController {
    FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GET
    @Path("/family/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamilyById(@PathParam("id") int id){
        try{
            return Response.ok(familyService.getFamilyById(id)).build();
        } catch (FailedToGetFamilyException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        catch (FamilyDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
