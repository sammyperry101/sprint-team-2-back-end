package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.kainos.ea.api.JobCapabilityService;
import org.kainos.ea.cli.CapabilityRequest;
import org.kainos.ea.client.CapabilityNameTooLongException;
import org.kainos.ea.client.FailedToAddJobCapabilityException;
import org.kainos.ea.client.FailedToGetJobCapabilityException;
import org.kainos.ea.client.JobCapabilityNotAddedException;
import org.kainos.ea.client.JobCapabilityNotFoundException;
import org.kainos.ea.validator.JobCapabilityValidator;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 Capability API")
@Path("/api")
public class JobCapabilityController {

    private JobCapabilityService jobCapabilityService;
    private JobCapabilityValidator jobCapabilityValidator;

    public JobCapabilityController(JobCapabilityService jobCapabilityService,
                                   JobCapabilityValidator jobCapabilityValidator) {
        this.jobCapabilityService = jobCapabilityService;
        this.jobCapabilityValidator = jobCapabilityValidator;
    }

    @GET
    @Path("/capability")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilities() {
        try {
            return Response.ok(jobCapabilityService.getAllCapabilities()).build();
        } catch (JobCapabilityNotFoundException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (FailedToGetJobCapabilityException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @POST
    @Path("/capability")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    @ApiOperation(value = "Add capability", authorizations ={
            @Authorization(value = HttpHeaders.AUTHORIZATION)
    })
    public Response addCapability(CapabilityRequest capability) {
        try {
            if (jobCapabilityValidator.isValidCapability(capability)) {
                return Response.ok(jobCapabilityService.addCapability(capability)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (JobCapabilityNotAddedException | CapabilityNameTooLongException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (FailedToAddJobCapabilityException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/capability/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilityById(@PathParam("id") int id) {
        try {
            return Response.ok(jobCapabilityService.getCapabilityById(id)).build();
        } catch (JobCapabilityNotFoundException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (FailedToGetJobCapabilityException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
