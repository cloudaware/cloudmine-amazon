package com.cloudaware.cloudmine.amazon.support;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.support.AWSSupport;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class SupportCaller {

    private SupportCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSSupport, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSSupport.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getAwsSupport());
    }
}
