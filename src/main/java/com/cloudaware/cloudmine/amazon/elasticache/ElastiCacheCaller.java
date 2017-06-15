package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.elasticache.AmazonElastiCache;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ElastiCacheCaller {

    private ElastiCacheCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonElastiCache, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonElastiCache.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getElastiCache(region));
    }
}
