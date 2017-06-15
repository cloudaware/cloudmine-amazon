package com.cloudaware.cloudmine.amazon.ecs;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.ecs.AmazonECS;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class EcsCaller {

    private EcsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonECS, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonECS.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getEcs(region));
    }
}
