package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class SesCaller {

    private SesCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonSimpleEmailService, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonSimpleEmailService.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getSes(region));
    }
}
