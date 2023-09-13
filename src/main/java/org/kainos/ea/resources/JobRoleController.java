package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.JobRolesNotFoundException;
//import org.kainos.ea.client.FailedToDeleteJobRoleException;
//import org.kainos.ea.client.JobRoleDoesNotExistException;

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

    /*@DELETE
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRole(@PathParam("id") int id) {
        try {
            jobRoleService.deleteRole(id);

            return Response.ok().build();
        } catch (JobRoleDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteJobRoleException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }*/

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewRoles(){
        try{
            return Response.ok(jobRoleService.viewRoles()).build();
        } catch (JobRolesNotFoundException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (FailedToGetJobRoles e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}