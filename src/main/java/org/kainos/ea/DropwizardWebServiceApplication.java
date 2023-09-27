package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.api.*;
import org.kainos.ea.db.*;
import org.kainos.ea.resources.*;
import org.kainos.ea.auth.TokenService;
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

        environment.jersey().register(new JobSpecController(new JobSpecService(new JobSpecDAO())));

        environment.jersey().register(new JobFamilyController(
                new JobFamilyService(new JobFamilyDao(new DatabaseConnector()))));
        environment.jersey().register(new JobCapabilityController(
                new JobCapabilityService(new JobCapabilityDao(new DatabaseConnector())), new JobCapabilityValidator()));
        environment.jersey().register(new JobRoleController(new JobRoleService(new JobRoleDao(new DatabaseConnector()))));
        environment.jersey().register(new AuthController(new AuthService(new AuthDao(new DatabaseConnector()), new TokenService())));
        environment.jersey().register(new HelloWorldController());
        environment.jersey().register(new AuthRoleController(new AuthRoleService(new AuthRoleDao())));
        environment.jersey().register(new JobBandController(new JobBandService(new JobBandDao(new DatabaseConnector()))));
    }
}
