package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.api.AuthRoleService;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.auth.JWTAuthenticator;
import org.kainos.ea.auth.JWTAuthorizer;
import org.kainos.ea.cli.User;
import org.kainos.ea.db.JobRoleDao;
import org.kainos.ea.db.AuthRoleDao;
import org.kainos.ea.resources.AuthRoleController;
import org.kainos.ea.resources.JobRoleController;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.auth.TokenService;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.resources.AuthController;
import org.kainos.ea.resources.HelloWorldController;

public class DropwizardWebServiceApplication extends Application<DropwizardWebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardWebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardWebService";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardWebServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardWebServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardWebServiceConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final DropwizardWebServiceConfiguration configuration,
                    final Environment environment) {
        JWTAuthenticator jwtAuthenticator = new JWTAuthenticator(new TokenService());

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>().setAuthenticator(jwtAuthenticator).setAuthorizer(new JWTAuthorizer()).setPrefix("Bearer").buildAuthFilter()));
        environment.jersey().register(new JobRoleController(new JobRoleService(new JobRoleDao(new DatabaseConnector()))));
        environment.jersey().register(new AuthController(new AuthService(new AuthDao(new DatabaseConnector()), new TokenService())));
        environment.jersey().register(new HelloWorldController());
        environment.jersey().register(new AuthRoleController(new AuthRoleService(new AuthRoleDao())));
    }

}
