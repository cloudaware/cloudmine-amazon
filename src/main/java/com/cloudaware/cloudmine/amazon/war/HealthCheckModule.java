package com.cloudaware.cloudmine.amazon.war;

import com.google.inject.servlet.ServletModule;

public class HealthCheckModule extends ServletModule {
    @Override
    protected void configureServlets() {
        super.configureServlets();
        serve("/liveness_check").with(LivenessCheckServlet.class);
        serve("/readiness_check").with(ReadinessCheckServlet.class);
    }
}
