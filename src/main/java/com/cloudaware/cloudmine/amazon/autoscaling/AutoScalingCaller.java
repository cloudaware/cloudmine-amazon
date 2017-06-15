package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.autoscaling.AmazonAutoScaling;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class AutoScalingCaller {

    private AutoScalingCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonAutoScaling, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonAutoScaling.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getAutoScaling(region));
    }
}
