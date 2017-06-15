package com.cloudaware.cloudmine.amazon.sts;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class StsCaller {

    private StsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSSecurityTokenService, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String partition
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSSecurityTokenService.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getSts(partition));
    }
}
