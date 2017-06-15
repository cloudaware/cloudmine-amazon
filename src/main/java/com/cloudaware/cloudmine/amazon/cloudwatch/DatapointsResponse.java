package com.cloudaware.cloudmine.amazon.cloudwatch;

import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:10
 */
public final class DatapointsResponse extends AmazonResponse {
    private List<Datapoint> datapoints;

    public List<Datapoint> getDatapoints() {
        return datapoints;
    }

    public void setDatapoints(final List<Datapoint> datapoints) {
        this.datapoints = datapoints;
    }
}
