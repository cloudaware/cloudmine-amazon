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
import com.amazonaws.services.dax.model.Tag;
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
import com.google.common.collect.Lists;

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
            name = "tags.get",
            path = "{region}/tags/ARN"
    )
    public TagsResponse tagsGet(
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
            name = "tags.create",
            path = "{region}/tags/create"
    )
    public AmazonResponse createTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(TagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }
            client.tagResource(r.withResourceName(request.getArn()).withTags(tags));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.delete",
            path = "{region}/tags/detele"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return DynamoDbAcceleratorCaller.get(UntagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.untagResource(r.withResourceName(request.getArn()).withTagKeys(request.getTags().keySet()));
        });
    }
}
