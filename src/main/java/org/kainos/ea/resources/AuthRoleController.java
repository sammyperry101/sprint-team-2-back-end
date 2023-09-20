package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthRoleService;
import org.kainos.ea.cli.AuthRole;
import org.kainos.ea.client.FailedToGetAuthRoles;
import org.kainos.ea.db.AuthRoleDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api("Authentication API")
@Path("/api")
public class AuthRoleController {
    AuthRoleService authRoleService = new AuthRoleService(new AuthRoleDao());
    @GET
    @Path("/authRoles")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getRoles(){
        try{
            List<AuthRole> authRoles = authRoleService.getAuthRoles();

            return Response.ok(authRoles).build();
        } catch (FailedToGetAuthRoles e) {
           return  Response.serverError().build();
        }
    }


}
