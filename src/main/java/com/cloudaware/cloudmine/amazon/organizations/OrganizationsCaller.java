package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.organizations.AWSOrganizations;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class OrganizationsCaller {

    private OrganizationsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSOrganizations, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSOrganizations.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getOrganizations());
    }
}
