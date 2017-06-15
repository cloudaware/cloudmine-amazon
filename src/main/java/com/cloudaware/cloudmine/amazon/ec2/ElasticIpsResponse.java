package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Address;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:14
 */
public final class ElasticIpsResponse extends AmazonResponse {
    private List<Address> elasticIps;

    public List<Address> getElasticIps() {
        return elasticIps;
    }

    public void setElasticIps(final List<Address> elasticIps) {
        this.elasticIps = elasticIps;
    }
}
