package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ElbCaller {

    private ElbCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonElasticLoadBalancing, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonElasticLoadBalancing.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getElb(region));
    }
}
