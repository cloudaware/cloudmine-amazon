package com.cloudaware.cloudmine.amazon.athena;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.athena.AmazonAthena;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class AthenaCaller {

    private AthenaCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonAthena, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonAthena.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getAthena(region));
    }
}
