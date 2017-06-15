package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.StreamingDistribution;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:02
 */
public final class StreamingDistributionResponse extends AmazonResponse {
    private StreamingDistribution streamingDistribution;

    public StreamingDistribution getStreamingDistribution() {
        return streamingDistribution;
    }

    public void setStreamingDistribution(final StreamingDistribution streamingDistribution) {
        this.streamingDistribution = streamingDistribution;
    }
}
