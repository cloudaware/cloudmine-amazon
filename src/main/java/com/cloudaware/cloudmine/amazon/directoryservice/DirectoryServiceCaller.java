package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.directory.AWSDirectoryService;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class DirectoryServiceCaller {

    private DirectoryServiceCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSDirectoryService, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSDirectoryService.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getDirectoryService(region));
    }
}
