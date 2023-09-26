package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.JobRoleFilter;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.JobRolesNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team 2 Role API")
@Path("/api")
public class JobRoleController {

    private JobRoleService jobRoleService;

    public JobRoleController(JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }
    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewRoles(){
        try{
            return Response.ok(jobRoleService.viewRoles()).build();
        } catch (JobRolesNotFoundException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (FailedToGetJobRoles e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @POST
    @Path("/job-roles/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewRolesWithFilter(JobRoleFilter filter){
        try{
            return Response.ok(jobRoleService.viewRolesWithFilter(filter)).build();
        } catch (FailedToGetJobRoles e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}