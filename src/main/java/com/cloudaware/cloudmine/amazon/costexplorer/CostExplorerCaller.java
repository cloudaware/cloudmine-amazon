package com.cloudaware.cloudmine.amazon.costexplorer;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.costexplorer.AWSCostExplorer;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CostExplorerCaller {

    private CostExplorerCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCostExplorer, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCostExplorer.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCostExplorer());
    }
}
