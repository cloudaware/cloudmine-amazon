package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class DynamoDbCaller {

    private DynamoDbCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonDynamoDB, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonDynamoDB.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getDynamoDb(region));
    }
}
