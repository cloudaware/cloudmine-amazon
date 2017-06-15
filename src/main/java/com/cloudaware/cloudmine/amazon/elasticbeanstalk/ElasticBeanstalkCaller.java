package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalk;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ElasticBeanstalkCaller {

    private ElasticBeanstalkCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSElasticBeanstalk, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSElasticBeanstalk.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getElasticBeanstalk(region));
    }
}
