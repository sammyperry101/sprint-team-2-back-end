package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.kainos.ea.api.AuthRoleService;
import org.kainos.ea.api.JobCapabilityService;
import org.kainos.ea.api.JobFamilyService;
import org.kainos.ea.auth.JWTFilter;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecDAO;
import org.kainos.ea.resources.JobSpecController;

import org.kainos.ea.db.JobCapabilityDao;
import org.kainos.ea.db.JobFamilyDao;
import org.kainos.ea.resources.JobCapabilityController;
import org.kainos.ea.resources.JobFamilyController;
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
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.resources.AuthController;
import org.kainos.ea.resources.HelloWorldController;
import org.kainos.ea.validator.JobCapabilityValidator;

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
        JWTAuthenticator jwtAuthenticator = new JWTAuthenticator(new TokenService(new AuthDao(new DatabaseConnector())));

        environment.jersey().register(new AuthDynamicFeature(new JWTFilter.Builder().setAuthenticator(jwtAuthenticator).
                setAuthorizer(new JWTAuthorizer()).setPrefix("Bearer").buildAuthFilter()));

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        environment.jersey().register(new JobSpecController(new JobSpecService(new JobSpecDAO())));

        environment.jersey().register(new JobFamilyController(
                new JobFamilyService(new JobFamilyDao(new DatabaseConnector()))));

        environment.jersey().register(new JobCapabilityController(
                new JobCapabilityService(new JobCapabilityDao(new DatabaseConnector())), new JobCapabilityValidator()));
        environment.jersey().register(new JobRoleController(new JobRoleService(new JobRoleDao(new DatabaseConnector()))));

        environment.jersey().register(new AuthController(new AuthService(new AuthDao(new DatabaseConnector()),
                new TokenService(new AuthDao(new DatabaseConnector())))));

        environment.jersey().register(new HelloWorldController());

        environment.jersey().register(new AuthRoleController(new AuthRoleService(new AuthRoleDao())));
    }
}
