package com.cloudaware.cloudmine.amazon.sns;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.sns.AmazonSNS;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class SnsCaller {

    private SnsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonSNS, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonSNS.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getSns(region));
    }
}
