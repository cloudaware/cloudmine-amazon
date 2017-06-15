package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.RouteTable;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:53
 */
public final class RouteTablesResponse extends AmazonResponse {
    private List<RouteTable> routeTables;

    public List<RouteTable> getRouteTables() {
        return routeTables;
    }

    public void setRouteTables(final List<RouteTable> routeTables) {
        this.routeTables = routeTables;
    }
}
