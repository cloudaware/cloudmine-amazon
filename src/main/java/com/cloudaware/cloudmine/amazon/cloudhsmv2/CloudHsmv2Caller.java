package com.cloudaware.cloudmine.amazon.cloudhsmv2;

import com.amazonaws.AmazonWebServiceRequest;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.amazonaws.services.cloudhsmv2.AWSCloudHSMV2;

final class CloudHsmv2Caller {
    private CloudHsmv2Caller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCloudHSMV2, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCloudHSMV2.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getAwsCloudHsmv2(region));
    }
}
