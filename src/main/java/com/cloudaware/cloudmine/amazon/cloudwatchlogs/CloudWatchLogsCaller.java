package com.cloudaware.cloudmine.amazon.cloudwatchlogs;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.logs.AWSLogs;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CloudWatchLogsCaller {

    private CloudWatchLogsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSLogs, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSLogs.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCwLogs(region));
    }
}
