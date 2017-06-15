package com.cloudaware.cloudmine.amazon.sqs;

import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.ListQueuesRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "sqs",
        canonicalName = "Sqs",
        title = "Amazon Simple Queue Service (SQS)",
        description = "Managed Message Queues",
        namespace = @ApiNamespace(
                ownerDomain = "cloudaware.com",
                ownerName = "CloudAware",
                packagePath = "cloudmine/amazon"
        ),
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        apiKeyRequired = AnnotationBoolean.TRUE
)
public final class SqsApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "queues.list",
            path = "{region}/queues"
    )
    public QueuesResponse queuesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return SqsCaller.get(ListQueuesRequest.class, QueuesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListQueuesResult result = client.listQueues(request);
            response.setQueueUrls(result.getQueueUrls());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "queues.attributes.list",
            path = "{region}/queues/QUEUE_URL/attributes"
    )
    public AttributesResponse queuesAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("queueUrl") final String queueUrl
    ) throws AmazonUnparsedException {
        return SqsCaller.get(GetQueueAttributesRequest.class, AttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetQueueAttributesResult result = client.getQueueAttributes(request.withQueueUrl(queueUrl).withAttributeNames("All"));
            response.setAttributes(result.getAttributes());
        });
    }
}
