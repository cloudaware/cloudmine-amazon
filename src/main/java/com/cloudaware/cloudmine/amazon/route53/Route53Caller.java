package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.route53.AmazonRoute53;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class Route53Caller {

    private Route53Caller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonRoute53, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonRoute53.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getRoute53());
    }
}
