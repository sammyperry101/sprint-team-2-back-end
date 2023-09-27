package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.BandService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.client.BandDoesNotExistException;
import org.kainos.ea.client.FailedToGetBandException;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 Band API")
@Path("/api")
public class BandController {
    BandService bandService;

    public BandController(BandService bandService){
        this.bandService = bandService;
    }

    @GET
    @Path("/band/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandById(@PathParam("id") int id){
        try{
            return Response.ok(bandService.getBandById(id)).build();
        } catch (FailedToGetBandException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        catch (BandDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
