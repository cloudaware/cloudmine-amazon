package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.storagegateway.AWSStorageGateway;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class StorageGatewayCaller {

    private StorageGatewayCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSStorageGateway, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSStorageGateway.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getStorageGateway(region));
    }
}
