package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Api("Hello World")
@RolesAllowed("Admin")

public class HelloWorldController {
    @GET
    @Path("/hello-world")
    @ApiOperation(value = "Returns the user that is currently logged in", authorizations ={
            @Authorization(value = HttpHeaders.AUTHORIZATION)
    })
    public Response getJobSpecByRoleId(){
        return Response.ok("Hello World").build();
    }
}