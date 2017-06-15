package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.VpnConnection;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 13:09
 */
public final class VpnConnectionsResponse extends AmazonResponse {
    private List<VpnConnection> vpnConnections;

    public List<VpnConnection> getVpnConnections() {
        return vpnConnections;
    }

    public void setVpnConnections(final List<VpnConnection> vpnConnections) {
        this.vpnConnections = vpnConnections;
    }
}
