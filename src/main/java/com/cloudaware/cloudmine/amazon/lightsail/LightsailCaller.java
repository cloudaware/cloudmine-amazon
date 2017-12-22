package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.lightsail.AmazonLightsail;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class LightsailCaller {

    private LightsailCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonLightsail, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonLightsail.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getLightsail(region));
    }
}
