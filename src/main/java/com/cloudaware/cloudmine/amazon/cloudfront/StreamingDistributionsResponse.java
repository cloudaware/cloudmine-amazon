package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.StreamingDistributionSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:01
 */
public final class StreamingDistributionsResponse extends AmazonResponse {
    private List<StreamingDistributionSummary> streamingDistributions;

    public List<StreamingDistributionSummary> getStreamingDistributions() {
        return streamingDistributions;
    }

    public void setStreamingDistributions(final List<StreamingDistributionSummary> streamingDistributions) {
        this.streamingDistributions = streamingDistributions;
    }
}
