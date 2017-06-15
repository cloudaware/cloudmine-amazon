package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.services.route53.model.HostedZone;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 20:56
 */
public final class HostedZonesResponse extends AmazonResponse {
    private List<HostedZone> hostedZones;

    public List<HostedZone> getHostedZones() {
        return hostedZones;
    }

    public void setHostedZones(final List<HostedZone> hostedZones) {
        this.hostedZones = hostedZones;
    }
}
