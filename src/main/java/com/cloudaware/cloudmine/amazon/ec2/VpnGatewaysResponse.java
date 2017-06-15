package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.VpnGateway;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 13:11
 */
public final class VpnGatewaysResponse extends AmazonResponse {
    private List<VpnGateway> vpnGateways;

    public List<VpnGateway> getVpnGateways() {
        return vpnGateways;
    }

    public void setVpnGateways(final List<VpnGateway> vpnGateways) {
        this.vpnGateways = vpnGateways;
    }
}
