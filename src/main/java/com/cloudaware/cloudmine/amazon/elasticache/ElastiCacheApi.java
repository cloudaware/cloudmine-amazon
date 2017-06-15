package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.services.elasticache.model.AddTagsToResourceRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheClustersRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheClustersResult;
import com.amazonaws.services.elasticache.model.DescribeCacheParameterGroupsRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheParameterGroupsResult;
import com.amazonaws.services.elasticache.model.DescribeCacheSecurityGroupsRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheSecurityGroupsResult;
import com.amazonaws.services.elasticache.model.DescribeCacheSubnetGroupsRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheSubnetGroupsResult;
import com.amazonaws.services.elasticache.model.DescribeEventsRequest;
import com.amazonaws.services.elasticache.model.DescribeEventsResult;
import com.amazonaws.services.elasticache.model.DescribeReplicationGroupsRequest;
import com.amazonaws.services.elasticache.model.DescribeReplicationGroupsResult;
import com.amazonaws.services.elasticache.model.DescribeReservedCacheNodesRequest;
import com.amazonaws.services.elasticache.model.DescribeReservedCacheNodesResult;
import com.amazonaws.services.elasticache.model.DescribeSnapshotsRequest;
import com.amazonaws.services.elasticache.model.DescribeSnapshotsResult;
import com.amazonaws.services.elasticache.model.ListTagsForResourceRequest;
import com.amazonaws.services.elasticache.model.ListTagsForResourceResult;
import com.amazonaws.services.elasticache.model.RemoveTagsFromResourceRequest;
import com.amazonaws.services.elasticache.model.Tag;
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
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "elasticache",
        canonicalName = "ElastiCache",
        title = "Amazon ElastiCache",
        description = "In-Memory Caching Syste",
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
public final class ElastiCacheApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/clusters"
    )
    public CacheClustersResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElastiCacheCaller.get(DescribeCacheClustersRequest.class, CacheClustersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeCacheClustersResult result = client.describeCacheClusters(
                    request
                            .withShowCacheNodeInfo(true)
                            .withMarker(page)
            );
            response.setCacheClusters(result.getCacheClusters());
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
        return ElastiCacheCaller.get(DescribeSnapshotsRequest.class, SnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSnapshotsResult result = client.describeSnapshots(
                    request
                            .withMarker(page)
            );
            response.setSnapshots(result.getSnapshots());
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
        return ElastiCacheCaller.get(DescribeEventsRequest.class, EventsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventsResult result = client.describeEvents(
                    request
                            .withMarker(page)
            );
            response.setEvents(result.getEvents());
            response.setNextPage(result.getMarker());
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
        return ElastiCacheCaller.get(DescribeCacheParameterGroupsRequest.class, ParameterGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeCacheParameterGroupsResult result = client.describeCacheParameterGroups(
                    request
                            .withMarker(page)
            );
            response.setParameterGroups(result.getCacheParameterGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "replicationGroups.list",
            path = "{region}/replication-groups"
    )
    public ReplicationGroupsResponse replicationGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElastiCacheCaller.get(DescribeReplicationGroupsRequest.class, ReplicationGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeReplicationGroupsResult result = client.describeReplicationGroups(
                    request
                            .withMarker(page)
            );
            response.setReplicationGroups(result.getReplicationGroups());
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
        return ElastiCacheCaller.get(DescribeReservedCacheNodesRequest.class, ReservedNodesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeReservedCacheNodesResult result = client.describeReservedCacheNodes(
                    request
                            .withMarker(page)
            );
            response.setReservedNodes(result.getReservedCacheNodes());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "securityGroups.list",
            path = "{region}/security-groups"
    )
    public CacheSecurityGroupsResponse securityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElastiCacheCaller.get(DescribeCacheSecurityGroupsRequest.class, CacheSecurityGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeCacheSecurityGroupsResult result = client.describeCacheSecurityGroups(
                    request
                            .withMarker(page)
            );
            response.setCacheSecurityGroups(result.getCacheSecurityGroups());
            response.setNextPage(result.getMarker());
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
        return ElastiCacheCaller.get(DescribeCacheSubnetGroupsRequest.class, SubnetGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeCacheSubnetGroupsResult result = client.describeCacheSubnetGroups(
                    request
                            .withMarker(page)
            );
            response.setSubnetGroups(result.getCacheSubnetGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.get",
            path = "{region}/tags/ARN"
    )
    public TagsResponse tagsForResource(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        return ElastiCacheCaller.get(ListTagsForResourceRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsForResourceResult result = client.listTagsForResource(request.withResourceName(arn));
            final Map<String, String> out = Maps.newHashMap();
            if (result != null && result.getTagList().size() > 0) {
                for (final Tag tag : result.getTagList()) {
                    out.put(tag.getKey(), tag.getValue());
                }
            }

            response.setTags(out);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.add",
            path = "{region}/tags/add"
    )
    public AmazonResponse addTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return ElastiCacheCaller.get(AddTagsToResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, addTagsToResourceRequest, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }

            client.addTagsToResource(
                    addTagsToResourceRequest
                            .withResourceName(request.getArn())
                            .withTags(tags)
            );
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.delete",
            path = "{region}/tags/delete"
    )
    public AmazonResponse deleteTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return ElastiCacheCaller.get(RemoveTagsFromResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, removeTagsFromResourceRequest, response) -> {
            client.removeTagsFromResource(
                    removeTagsFromResourceRequest
                            .withResourceName(request.getArn())
                            .withTagKeys(request.getTags().keySet())
            );
        });
    }
}
