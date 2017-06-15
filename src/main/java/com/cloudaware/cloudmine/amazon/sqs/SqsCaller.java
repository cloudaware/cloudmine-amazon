package com.cloudaware.cloudmine.amazon.sqs;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class SqsCaller {

    private SqsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonSQS, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonSQS.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getSqs(region));
    }
}
