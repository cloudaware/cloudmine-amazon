package com.cloudaware.cloudmine.amazon.dynamodbaccelerator;

import com.amazonaws.services.dax.model.DescribeClustersRequest;
import com.amazonaws.services.dax.model.DescribeClustersResult;
import com.amazonaws.services.dax.model.DescribeDefaultParametersRequest;
import com.amazonaws.services.dax.model.DescribeDefaultParametersResult;
import com.amazonaws.services.dax.model.DescribeEventsRequest;
import com.amazonaws.services.dax.model.DescribeEventsResult;
import com.amazonaws.services.dax.model.DescribeParameterGroupsRequest;
import com.amazonaws.services.dax.model.DescribeParameterGroupsResult;
import com.amazonaws.services.dax.model.DescribeParametersRequest;
import com.amazonaws.services.dax.model.DescribeParametersResult;
import com.amazonaws.services.dax.model.DescribeSubnetGroupsRequest;
import com.amazonaws.services.dax.model.DescribeSubnetGroupsResult;
import com.amazonaws.services.dax.model.ListTagsRequest;
import com.amazonaws.services.dax.model.ListTagsResult;
import com.amazonaws.services.dax.model.TagResourceRequest;
import com.amazonaws.services.dax.model.UntagResourceRequest;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.util.List;

@Api(
        name = "dynamodbaccelerator",
        canonicalName = "DynamoDbAccelerator",
        title = "Amazon DynamoDB Accelerator",
        description = "Managed NoSQL Database",
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
public final class DynamoDbAcceleratorApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/clusters"
    )
    public ClustersResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(DescribeClustersRequest.class, ClustersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClustersResult result = client.describeClusters(
                    request.withNextToken(page)
            );
            response.setClusters(result.getClusters());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subnetGroups.list",
            path = "{region}/subnet-groups"
    )
    public SubnetGroupsResponse subnetGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(DescribeSubnetGroupsRequest.class, SubnetGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSubnetGroupsResult result = client.describeSubnetGroups(
                    request.withNextToken(page)
            );
            response.setSubnetGroups(result.getSubnetGroups());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parameterGroups.list",
            path = "{region}/parameter-groups"
    )
    public ParameterGroupsResponse parameterGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(DescribeParameterGroupsRequest.class, ParameterGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeParameterGroupsResult result = client.describeParameterGroups(
                    request.withNextToken(page)
            );
            response.setParameterGroups(result.getParameterGroups());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parameterGroups.parameters.list",
            path = "{region}/parameter-groups/{parameterGroupName}/parameters"
    )
    public ParametersResponse parametersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("parameterGroupName") final String parameterGroupName
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(DescribeParametersRequest.class, ParametersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeParametersResult result = client.describeParameters(
                    request.withParameterGroupName(parameterGroupName).withNextToken(page)
            );
            response.setParameters(result.getParameters());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "defaultParameters.list",
            path = "{region}/default-parameters"
    )
    public ParametersResponse defaultParametersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(DescribeDefaultParametersRequest.class, ParametersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDefaultParametersResult result = client.describeDefaultParameters(
                    request.withNextToken(page)
            );
            response.setParameters(result.getParameters());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "events.list",
            path = "{region}/events"
    )
    public EventsResponse eventsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(DescribeEventsRequest.class, EventsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventsResult result = client.describeEvents(
                    request.withNextToken(page)
            );
            response.setEvents(result.getEvents());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "resources.tags.get",
            path = "{region}/resources/ARN/tags"
    )
    public TagsResponse resourcesTagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(ListTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsResult result = client.listTags(request.withNextToken(page).withResourceName(arn));
            response.setTags(result.getTags());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "resources.tags.tag",
            path = "{region}/resources/ARN/tags"
    )
    public AmazonResponse resourcesTagsTag(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn,
            final TagTagsRequest request
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(TagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.tagResource(r.withResourceName(arn).withTags(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "resources.tags.untag",
            path = "{region}/resources/ARN/tags"
    )
    public AmazonResponse resourcesTagsUntag(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn,
            @Named("tagKey") final List<String> tagKeys
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(UntagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.untagResource(r.withResourceName(arn).withTagKeys(tagKeys));
        });
    }
}
