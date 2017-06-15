package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.workspaces.AmazonWorkspaces;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class WorkspacesCaller {

    private WorkspacesCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonWorkspaces, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonWorkspaces.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getWorkspaces(region));
    }
}
