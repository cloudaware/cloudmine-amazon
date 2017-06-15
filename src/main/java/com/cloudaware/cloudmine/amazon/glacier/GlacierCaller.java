package com.cloudaware.cloudmine.amazon.glacier;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.glacier.AmazonGlacier;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class GlacierCaller {

    private GlacierCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonGlacier, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonGlacier.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getGlacier(region));
    }
}
