package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.CloudFrontOriginAccessIdentity;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:39
 */
public final class OriginAccessIdentityResponse extends AmazonResponse {
    private CloudFrontOriginAccessIdentity originAccessIdentity;

    public CloudFrontOriginAccessIdentity getOriginAccessIdentity() {
        return originAccessIdentity;
    }

    public void setOriginAccessIdentity(final CloudFrontOriginAccessIdentity originAccessIdentity) {
        this.originAccessIdentity = originAccessIdentity;
    }
}
