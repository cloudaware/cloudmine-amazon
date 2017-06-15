package com.cloudaware.cloudmine.amazon.kms;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.kms.AWSKMS;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class KmsCaller {

    private KmsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSKMS, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSKMS.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getKms(region));
    }
}
