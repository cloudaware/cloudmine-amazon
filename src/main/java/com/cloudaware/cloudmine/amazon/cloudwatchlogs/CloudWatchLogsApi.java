package com.cloudaware.cloudmine.amazon.cloudwatchlogs;

import com.amazonaws.services.logs.model.DescribeLogGroupsRequest;
import com.amazonaws.services.logs.model.DescribeLogGroupsResult;
import com.amazonaws.services.logs.model.DescribeLogStreamsRequest;
import com.amazonaws.services.logs.model.DescribeLogStreamsResult;
import com.amazonaws.services.logs.model.DescribeMetricFiltersRequest;
import com.amazonaws.services.logs.model.DescribeMetricFiltersResult;
import com.amazonaws.services.logs.model.DescribeSubscriptionFiltersRequest;
import com.amazonaws.services.logs.model.DescribeSubscriptionFiltersResult;
import com.amazonaws.services.logs.model.ListTagsLogGroupRequest;
import com.amazonaws.services.logs.model.ListTagsLogGroupResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "cloudwatchlogs",
        canonicalName = "CloudWatchLogs",
        title = "Amazon CloudWatch Logs",
        description = "Monitor, store, and access your log files",
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
public final class CloudWatchLogsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "logGroups.list",
            path = "{region}/log-groups"
    )
    public LogGroupsResponse logGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudWatchLogsCaller.get(DescribeLogGroupsRequest.class, LogGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLogGroupsResult result = client.describeLogGroups(
                    request.withNextToken(page)
            );
            response.setLogGroups(result.getLogGroups());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "logGroups.logStreams.list",
            path = "{region}/log-groups/{logGroupName}/log-streams"
    )
    public LogStreamsResponse logGroupsLogStreamsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("logGroupName") final String logGroupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudWatchLogsCaller.get(DescribeLogStreamsRequest.class, LogStreamsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLogStreamsResult result = client.describeLogStreams(
                    request.withLogGroupName(logGroupName).withNextToken(page)
            );
            response.setLogStreams(result.getLogStreams());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "logGroups.tags.list",
            path = "{region}/log-groups/{logGroupName}/tags"
    )
    public TagsResponse logGroupsTagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("logGroupName") final String logGroupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudWatchLogsCaller.get(ListTagsLogGroupRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsLogGroupResult result = client.listTagsLogGroup(
                    request.withLogGroupName(logGroupName)
            );
            response.setTags(result.getTags());
            response.setNextPage(page);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "logGroups.subscriptionFilters.list",
            path = "{region}/log-groups/{logGroupName}/subscription-filters"
    )
    public SubscriptionFiltersResponse logGroupsSubscriptionFiltersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("logGroupName") final String logGroupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudWatchLogsCaller.get(DescribeSubscriptionFiltersRequest.class, SubscriptionFiltersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSubscriptionFiltersResult result = client.describeSubscriptionFilters(
                    request.withLogGroupName(logGroupName).withNextToken(page)
            );
            response.setSubscriptionFilters(result.getSubscriptionFilters());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "metricFilters.list",
            path = "{region}/metric-filters"
    )
    public MetricFiltersResponse metricFiltersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudWatchLogsCaller.get(DescribeMetricFiltersRequest.class, MetricFiltersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeMetricFiltersResult result = client.describeMetricFilters(
                    request.withNextToken(page)
            );
            response.setMetricFilters(result.getMetricFilters());
            response.setNextPage(result.getNextToken());
        });
    }

}
