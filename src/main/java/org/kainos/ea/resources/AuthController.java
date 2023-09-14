package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.LoginResponse;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Api("Authentication API")
@Path("/api")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest login) {
        try {
            ImmutablePair<User, String> response = authService.login(login);
            if(response == null){
                return Response.status(400).build();
            }
            LoginResponse loginResponse = new LoginResponse(
                    response.right,
                    response.left.getEmail()
            );
            return Response.ok(loginResponse).build();
        } catch (FailedLoginException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}

