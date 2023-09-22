package org.kainos.ea.auth;

import io.dropwizard.auth.AuthFilter;
import org.kainos.ea.cli.User;

import javax.ws.rs.container.ContainerRequestContext;

public class JWTFilter extends AuthFilter<String, User> {
    @Override
    public void filter(ContainerRequestContext containerRequestContext){
        String header = containerRequestContext.getHeaders().getFirst("Authorization");

        String token = header.substring("Bearer".length()).trim();



    }

}
