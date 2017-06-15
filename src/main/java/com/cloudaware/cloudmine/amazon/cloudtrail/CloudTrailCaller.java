package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.cloudtrail.AWSCloudTrail;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CloudTrailCaller {

    private CloudTrailCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCloudTrail, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCloudTrail.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCloudTrail(region));
    }
}
