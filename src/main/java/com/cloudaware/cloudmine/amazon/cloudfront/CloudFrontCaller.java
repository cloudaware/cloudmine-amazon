package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.cloudfront.AmazonCloudFront;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CloudFrontCaller {

    private CloudFrontCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonCloudFront, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonCloudFront.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCloudFront());
    }
}
