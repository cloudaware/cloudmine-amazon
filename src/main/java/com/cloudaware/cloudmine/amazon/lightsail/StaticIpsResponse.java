package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.services.lightsail.model.StaticIp;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StaticIpsResponse extends AmazonResponse {

    private List<StaticIp> staticIps;

    public List<StaticIp> getStaticIps() {
        return staticIps;
    }

    public void setStaticIps(final List<StaticIp> staticIps) {
        this.staticIps = staticIps;
    }
}
