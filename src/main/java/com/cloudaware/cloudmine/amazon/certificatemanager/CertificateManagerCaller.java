package com.cloudaware.cloudmine.amazon.certificatemanager;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.certificatemanager.AWSCertificateManager;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CertificateManagerCaller {

    private CertificateManagerCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCertificateManager, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCertificateManager.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCertificateManager(region));
    }
}
