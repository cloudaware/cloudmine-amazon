package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CloudFormationCaller {

    private CloudFormationCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonCloudFormation, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonCloudFormation.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCloudFormation(region));
    }
}
