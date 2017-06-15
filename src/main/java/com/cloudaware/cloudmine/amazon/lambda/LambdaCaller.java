package com.cloudaware.cloudmine.amazon.lambda;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.lambda.AWSLambda;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class LambdaCaller {

    private LambdaCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSLambda, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSLambda.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getLambda(region));
    }
}
