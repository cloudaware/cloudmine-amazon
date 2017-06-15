package com.cloudaware.cloudmine.amazon.sns;

import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesResult;
import com.amazonaws.services.sns.model.GetTopicAttributesRequest;
import com.amazonaws.services.sns.model.GetTopicAttributesResult;
import com.amazonaws.services.sns.model.ListSubscriptionsRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsResult;
import com.amazonaws.services.sns.model.ListTopicsRequest;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "sns",
        canonicalName = "Sns",
        title = "Amazon Simple Notification Service (SNS)",
        description = "Pub/Sub, Mobile Push and SMS",
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
public final class SnsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "topics.list",
            path = "{region}/topics"
    )
    public TopicsResponse topicsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return SnsCaller.get(ListTopicsRequest.class, TopicsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTopicsResult result = client.listTopics(request.withNextToken(page));
            response.setTopics(result.getTopics());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "topics.attributes.list",
            path = "{region}/topics/TOPIC_ARN/attributes"
    )
    public AttributesResponse topicsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("topicArn") final String topicArn
    ) throws AmazonUnparsedException {
        return SnsCaller.get(GetTopicAttributesRequest.class, AttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetTopicAttributesResult result = client.getTopicAttributes(request.withTopicArn(topicArn));
            response.setAttributes(result.getAttributes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "topics.publish",
            path = "{region}/topics/TOPIC_ARN"
    )
    public PublishResponse publish(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("topicArn") final String topicArn,
            final PublishRequest request
    ) throws AmazonUnparsedException {
        return SnsCaller.get(com.amazonaws.services.sns.model.PublishRequest.class, PublishResponse.class, credentials, region).execute((client, r, response) -> {
            final PublishResult result = client.publish(
                    r
                            .withTopicArn(topicArn)
                            .withTargetArn(request.getTargetArn())
                            .withPhoneNumber(request.getPhoneNumber())
                            .withMessage(request.getMessage())
                            .withSubject(request.getSubject())
                            .withMessageStructure(request.getMessageStructure())
                            .withMessageAttributes(request.getMessageAttributes())
            );
            response.setMessageId(result.getMessageId());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subscriptions.list",
            path = "{region}/subscriptions"
    )
    public SubscriptionsResponse subscriptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return SnsCaller.get(ListSubscriptionsRequest.class, SubscriptionsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListSubscriptionsResult result = client.listSubscriptions(request.withNextToken(page));
            response.setSubscriptions(result.getSubscriptions());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subscriptions.attributes.list",
            path = "{region}/subscriptions/SUBSCRIPTION_ARN"
    )
    public AttributesResponse subscriptionsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("subscriptionArn") final String subscriptionArn
    ) throws AmazonUnparsedException {
        return SnsCaller.get(GetSubscriptionAttributesRequest.class, AttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetSubscriptionAttributesResult result = client.getSubscriptionAttributes(request.withSubscriptionArn(subscriptionArn));
            response.setAttributes(result.getAttributes());
        });
    }
}
