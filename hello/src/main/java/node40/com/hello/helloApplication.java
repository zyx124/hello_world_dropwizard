package node40.com.hello;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import node40.com.hello.health.TemplateHealthCheck;
import node40.com.hello.resources.HelloWorldResource;

public class helloApplication extends Application<helloConfiguration> {

    public static void main(final String[] args) throws Exception {
        new helloApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public void initialize(final Bootstrap<helloConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(helloConfiguration configuration,
                    Environment environment) {
        // TODO: implement application
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
