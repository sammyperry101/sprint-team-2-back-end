package org.kainos.ea.resources;

import io.dropwizard.auth.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiParam;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.LoginResponse;
import org.kainos.ea.cli.RegisterRequest;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedLoginException;
import org.kainos.ea.client.FailedToGetRoleException;
import org.kainos.ea.client.FailedToRegisterException;


import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Authentication API")
@Path("/api")
@SwaggerDefinition(
        securityDefinition = @SecurityDefinition(
                apiKeyAuthDefinitions = {
                        @ApiKeyAuthDefinition(
                                key = HttpHeaders.AUTHORIZATION,
                                name = HttpHeaders.AUTHORIZATION,
                                in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER
                        )
                }
        )
)
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
            String response = authService.login(login);

            LoginResponse loginResponse = new LoginResponse(
                    response
            );
            return Response.ok(loginResponse).build();
        } catch (FailedLoginException e ) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }catch(SQLException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterRequest request) {

        try {
            authService.register(request);

            return Response.status(Response.Status.CREATED).build();
        } catch (FailedToRegisterException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }catch(SQLException | FailedToGetRoleException e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/whoami")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the user that is logged in", response = User.class, authorizations = {
            @Authorization(value = HttpHeaders.AUTHORIZATION)
    })
    public Response whoami(@Auth @ApiParam(hidden = true) User user) {
        return Response.ok().entity(new User(user.getUserId(), user.getEmail(), user.getRole())).build();
    }
}

