package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.rds.AmazonRDS;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class RdsCaller {

    private RdsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonRDS, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonRDS.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getRds(region));
    }
}
