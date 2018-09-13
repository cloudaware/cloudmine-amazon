package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.SpotFleetRequestConfig;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SpotFleetRequestsResponse extends AmazonResponse {
    private List<SpotFleetRequestConfig> spotFleetRequestConfigs;

    public List<SpotFleetRequestConfig> getSpotFleetRequestConfigs() {
        return spotFleetRequestConfigs;
    }

    public void setSpotFleetRequestConfigs(final List<SpotFleetRequestConfig> spotFleetRequestConfigs) {
        this.spotFleetRequestConfigs = spotFleetRequestConfigs;
    }
}
