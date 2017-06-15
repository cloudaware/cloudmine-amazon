package com.cloudaware.cloudmine.amazon.ssm;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class SsmCaller {

    private SsmCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSSimpleSystemsManagement, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSSimpleSystemsManagement.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getSsm(region));
    }
}
