package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.CloudFrontOriginAccessIdentitySummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:38
 */
public final class OriginAccessIdentitiesResponse extends AmazonResponse {
    private List<CloudFrontOriginAccessIdentitySummary> originAccessIdentitySummaries;

    public List<CloudFrontOriginAccessIdentitySummary> getOriginAccessIdentitySummaries() {
        return originAccessIdentitySummaries;
    }

    public void setOriginAccessIdentitySummaries(final List<CloudFrontOriginAccessIdentitySummary> originAccessIdentitySummaries) {
        this.originAccessIdentitySummaries = originAccessIdentitySummaries;
    }
}
