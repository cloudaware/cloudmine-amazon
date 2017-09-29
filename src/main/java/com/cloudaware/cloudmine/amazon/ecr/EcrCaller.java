package com.cloudaware.cloudmine.amazon.ecr;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.ecr.AmazonECR;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class EcrCaller {

    private EcrCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonECR, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonECR.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getEcr(region));
    }
}
