package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Subnet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 19:00
 */
public final class SubnetsResponse extends AmazonResponse {
    private List<Subnet> subnets;

    public List<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(final List<Subnet> subnets) {
        this.subnets = subnets;
    }
}
