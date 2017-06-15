package com.cloudaware.cloudmine.amazon.directconnect;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.directconnect.AmazonDirectConnect;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class DirectConnectCaller {

    private DirectConnectCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonDirectConnect, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonDirectConnect.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getDirectConnect(region));
    }
}
