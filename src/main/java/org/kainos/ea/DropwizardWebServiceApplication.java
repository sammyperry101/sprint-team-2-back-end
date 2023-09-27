package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.api.BandService;
import org.kainos.ea.api.FamilyService;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.db.*;
import org.kainos.ea.resources.*;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.auth.TokenService;

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
        environment.jersey().register(new JobRoleController(new JobRoleService(new JobRoleDao(new DatabaseConnector()))));
        environment.jersey().register(new FamilyController(new FamilyService(new FamilyDao())));
        environment.jersey().register(new BandController(new BandService(new BandDao())));
        environment.jersey().register(new AuthController(new AuthService(new AuthDao(new DatabaseConnector()), new TokenService())));
        environment.jersey().register(new HelloWorldController());
    }

}
