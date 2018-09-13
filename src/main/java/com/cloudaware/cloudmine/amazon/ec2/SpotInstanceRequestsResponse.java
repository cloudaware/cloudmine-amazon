package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.SpotInstanceRequest;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SpotInstanceRequestsResponse extends AmazonResponse {
    private List<SpotInstanceRequest> spotInstanceResponses;

    public List<SpotInstanceRequest> getSpotInstanceResponses() {
        return spotInstanceResponses;
    }

    public void setSpotInstanceResponses(final List<SpotInstanceRequest> spotInstanceResponses) {
        this.spotInstanceResponses = spotInstanceResponses;
    }
}
