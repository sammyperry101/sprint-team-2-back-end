package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobFamilyService;
import org.kainos.ea.client.FailedToGetJobFamilyException;
import org.kainos.ea.client.JobFamilyNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 Role API")
@Path("/api")
public class JobFamilyController {

    private JobFamilyService jobFamilyService;

    public JobFamilyController(JobFamilyService jobFamilyService) {
        this.jobFamilyService = jobFamilyService;
    }

    @GET
    @Path("/view-families-by-capability/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamilyByCapability(@PathParam("id") int id) {
        try {
            return Response.ok(jobFamilyService.getFamilyByCapability(id)).build();
        } catch (JobFamilyNotFoundException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (FailedToGetJobFamilyException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}