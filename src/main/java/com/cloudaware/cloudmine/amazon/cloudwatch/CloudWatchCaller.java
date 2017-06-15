package com.cloudaware.cloudmine.amazon.cloudwatch;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CloudWatchCaller {

    private CloudWatchCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonCloudWatch, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonCloudWatch.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCw(region));
    }
}
