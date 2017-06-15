package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.ec2.AmazonEC2;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class Ec2Caller {

    private Ec2Caller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonEC2, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonEC2.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getEc2(region));
    }
}
