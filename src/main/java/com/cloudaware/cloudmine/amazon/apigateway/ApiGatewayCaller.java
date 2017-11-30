package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ApiGatewayCaller {

    private ApiGatewayCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonApiGateway, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonApiGateway.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getApiGateway(region));
    }
}
