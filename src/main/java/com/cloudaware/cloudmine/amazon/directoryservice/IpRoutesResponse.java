package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.IpRouteInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IpRoutesResponse extends AmazonResponse {
    private List<IpRouteInfo> ipRoutes;

    public List<IpRouteInfo> getIpRoutes() {
        return ipRoutes;
    }

    public void setIpRoutes(final List<IpRouteInfo> ipRoutes) {
        this.ipRoutes = ipRoutes;
    }
}
