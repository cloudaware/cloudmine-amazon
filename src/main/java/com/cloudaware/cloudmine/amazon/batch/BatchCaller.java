package com.cloudaware.cloudmine.amazon.batch;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.batch.AWSBatch;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class BatchCaller {

    private BatchCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSBatch, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSBatch.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getBatch(region));
    }
}
