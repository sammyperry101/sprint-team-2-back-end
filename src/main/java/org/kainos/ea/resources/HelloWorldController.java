package org.kainos.ea.resources;

import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Hello World")
@Path("/api")
public class HelloWorldController {
    @GET
    @Path("/hello-world")
    public Response getJobSpecByRoleId(){
        return Response.ok("Hello World").build();
    }
}
