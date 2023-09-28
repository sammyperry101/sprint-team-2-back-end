package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobBandService;
import org.kainos.ea.client.FailedToGetJobBandException;
import org.kainos.ea.client.JobBandNotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 Band API")
@Path("/api")
public class JobBandController {
    private JobBandService jobBandService;

    public JobBandController(JobBandService jobBandService) {
        this.jobBandService = jobBandService;
    }
    @GET
    @Path("/band")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBands() {
        try {
            return Response.ok(jobBandService.getAllBands()).build();
        } catch (JobBandNotFoundException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (FailedToGetJobBandException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
