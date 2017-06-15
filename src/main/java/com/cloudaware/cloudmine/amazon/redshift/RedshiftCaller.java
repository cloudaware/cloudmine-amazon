package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.redshift.AmazonRedshift;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class RedshiftCaller {

    private RedshiftCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonRedshift, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonRedshift.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getRedshift(region));
    }
}
