package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.client.CapabilitiesWithFamilyIDNotFoundException;
import org.kainos.ea.client.FailedToGetCapabilitiesWithFamilyIDException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 Capability API")
@Path("/api")
public class CapabilityController {

    CapabilityService capabilityService;

    public CapabilityController(CapabilityService capabilityService) {
        this.capabilityService = capabilityService;
    }

    @GET
    @Path("/capabilities/familyID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilitiesWithFamilyID(){
        try{
            return Response.ok(capabilityService.getCapabilitiesWithFamilyID()).build();
        } catch (CapabilitiesWithFamilyIDNotFoundException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (FailedToGetCapabilitiesWithFamilyIDException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
