package com.cloudaware.cloudmine.amazon.war;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class WarContextListener extends com.google.inject.servlet.GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new GuiceSssModule() {
                    @Override
                    protected void configureServlets() {
                        super.configureServlets();
                        filterRegex("/_ah/api/(?!explorer)(?!static)(?!discovery).*").through(AuthFilter.class);
                    }
                },
                new HealthCheckModule()
        );
    }
}
