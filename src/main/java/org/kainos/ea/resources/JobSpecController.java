package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobSpecDoesNotExist;
import org.kainos.ea.model.JobSpec;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 JobSpec API")
@Path("/api")
public class JobSpecController {
    private final JobSpecService jobSpecService;

    public JobSpecController(JobSpecService jobSpecService) {
        this.jobSpecService = jobSpecService;
    }

    @GET
    @Path("/job-specification/{roleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecByRoleId(@PathParam("roleId") int roleId){
        try {
            JobSpec jobSpec = jobSpecService.getJobSpecByRoleId(roleId);

            return Response.ok(jobSpec).build();
        } catch (FailedToGetJobSpecException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (JobSpecDoesNotExist e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
