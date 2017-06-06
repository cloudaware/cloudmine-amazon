package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.CreateTagsRequest;
import com.amazonaws.services.redshift.model.DeleteTagsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterParameterGroupsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterParameterGroupsResult;
import com.amazonaws.services.redshift.model.DescribeClusterSecurityGroupsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterSecurityGroupsResult;
import com.amazonaws.services.redshift.model.DescribeClusterSnapshotsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterSnapshotsResult;
import com.amazonaws.services.redshift.model.DescribeClustersRequest;
import com.amazonaws.services.redshift.model.DescribeClustersResult;
import com.amazonaws.services.redshift.model.DescribeEventsRequest;
import com.amazonaws.services.redshift.model.DescribeEventsResult;
import com.amazonaws.services.redshift.model.DescribeReservedNodesRequest;
import com.amazonaws.services.redshift.model.DescribeReservedNodesResult;
import com.amazonaws.services.redshift.model.DescribeTagsRequest;
import com.amazonaws.services.redshift.model.DescribeTagsResult;
import com.amazonaws.services.redshift.model.Tag;
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

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "redshift",
        canonicalName = "Redshift",
        title = "Amazon Redshift",
        description = "Fast, Simple, Cost-Effective Data Warehousing",
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
public final class RedshiftApi {

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
        return AmazonRedshiftCaller.get(DescribeClustersRequest.class, ClustersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClustersResult result = client.describeClusters(request.withMarker(page));
            response.setClusters(result.getClusters());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "snapshots.list",
            path = "{region}/snapshots"
    )
    public SnapshotsResponse snapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AmazonRedshiftCaller.get(DescribeClusterSnapshotsRequest.class, SnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterSnapshotsResult result = client.describeClusterSnapshots(request.withMarker(page));
            response.setSnapshots(result.getSnapshots());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "reservedNodes.list",
            path = "{region}/reserved-nodes"
    )
    public ReservedNodesResponse reservedNodesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AmazonRedshiftCaller.get(DescribeReservedNodesRequest.class, ReservedNodesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeReservedNodesResult result = client.describeReservedNodes(request.withMarker(page));
            response.setReservedNodes(result.getReservedNodes());
            response.setNextPage(result.getMarker());
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
        return AmazonRedshiftCaller.get(DescribeEventsRequest.class, EventsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventsResult result = client.describeEvents(request.withMarker(page));
            response.setEvents(result.getEvents());
            response.setNextPage(result.getMarker());
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
            @Named("arn") final String arn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AmazonRedshiftCaller.get(DescribeTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTagsResult result = client.describeTags(request.withMarker(page));
            response.setTags(result.getTaggedResources());
            response.setNextPage(result.getMarker());
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
        return AmazonRedshiftCaller.get(CreateTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }

            client.createTags(r.withResourceName(request.getArn()).withTags(tags));
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
        return AmazonRedshiftCaller.get(DeleteTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.deleteTags(r.withResourceName(request.getArn()).withTagKeys(request.getTags().keySet()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parameterGroups.list",
            path = "{region}/parameter-groups"
    )
    public ParameterGroupResponse parameterGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AmazonRedshiftCaller.get(DescribeClusterParameterGroupsRequest.class, ParameterGroupResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterParameterGroupsResult result = client.describeClusterParameterGroups(request.withMarker(page));
            response.setParameterGroups(result.getParameterGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "securityGroups.list",
            path = "{region}/security-groups"
    )
    public SecurityGroupResponse securityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AmazonRedshiftCaller.get(DescribeClusterSecurityGroupsRequest.class, SecurityGroupResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterSecurityGroupsResult result = client.describeClusterSecurityGroups(request.withMarker(page));
            response.setSecurityGroups(result.getClusterSecurityGroups());
            response.setNextPage(result.getMarker());
        });
    }
}
