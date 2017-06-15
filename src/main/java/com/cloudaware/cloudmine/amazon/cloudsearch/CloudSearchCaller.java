package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.cloudsearchv2.AmazonCloudSearch;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CloudSearchCaller {

    private CloudSearchCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonCloudSearch, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonCloudSearch.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCloudSearch(region));
    }
}
