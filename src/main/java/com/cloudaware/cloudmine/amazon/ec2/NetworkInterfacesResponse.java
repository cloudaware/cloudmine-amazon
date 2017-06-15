package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.NetworkInterface;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:38
 */
public final class NetworkInterfacesResponse extends AmazonResponse {
    private List<NetworkInterface> networkInterfaces;

    public List<NetworkInterface> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public void setNetworkInterfaces(final List<NetworkInterface> networkInterfaces) {
        this.networkInterfaces = networkInterfaces;
    }
}
