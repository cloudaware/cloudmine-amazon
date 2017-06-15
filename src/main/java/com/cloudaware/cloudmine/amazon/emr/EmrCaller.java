package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class EmrCaller {

    private EmrCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonElasticMapReduce, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonElasticMapReduce.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getEmr(region));
    }
}
