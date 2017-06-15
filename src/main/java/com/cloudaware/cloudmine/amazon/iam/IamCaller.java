package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class IamCaller {

    private IamCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonIdentityManagement, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonIdentityManagement.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getIdentityManagement(region));
    }
}
