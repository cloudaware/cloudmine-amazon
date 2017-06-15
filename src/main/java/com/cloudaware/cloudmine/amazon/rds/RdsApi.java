package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.AddTagsToResourceRequest;
import com.amazonaws.services.rds.model.DBClusterSnapshotAttributesResult;
import com.amazonaws.services.rds.model.DBSnapshotAttributesResult;
import com.amazonaws.services.rds.model.DescribeAccountAttributesRequest;
import com.amazonaws.services.rds.model.DescribeAccountAttributesResult;
import com.amazonaws.services.rds.model.DescribeDBClusterParameterGroupsRequest;
import com.amazonaws.services.rds.model.DescribeDBClusterParameterGroupsResult;
import com.amazonaws.services.rds.model.DescribeDBClusterSnapshotAttributesRequest;
import com.amazonaws.services.rds.model.DescribeDBClusterSnapshotsRequest;
import com.amazonaws.services.rds.model.DescribeDBClusterSnapshotsResult;
import com.amazonaws.services.rds.model.DescribeDBClustersRequest;
import com.amazonaws.services.rds.model.DescribeDBClustersResult;
import com.amazonaws.services.rds.model.DescribeDBInstancesRequest;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;
import com.amazonaws.services.rds.model.DescribeDBParameterGroupsRequest;
import com.amazonaws.services.rds.model.DescribeDBParameterGroupsResult;
import com.amazonaws.services.rds.model.DescribeDBSecurityGroupsRequest;
import com.amazonaws.services.rds.model.DescribeDBSecurityGroupsResult;
import com.amazonaws.services.rds.model.DescribeDBSnapshotAttributesRequest;
import com.amazonaws.services.rds.model.DescribeDBSnapshotsRequest;
import com.amazonaws.services.rds.model.DescribeDBSnapshotsResult;
import com.amazonaws.services.rds.model.DescribeDBSubnetGroupsRequest;
import com.amazonaws.services.rds.model.DescribeDBSubnetGroupsResult;
import com.amazonaws.services.rds.model.DescribeEventsRequest;
import com.amazonaws.services.rds.model.DescribeEventsResult;
import com.amazonaws.services.rds.model.DescribeReservedDBInstancesRequest;
import com.amazonaws.services.rds.model.DescribeReservedDBInstancesResult;
import com.amazonaws.services.rds.model.ListTagsForResourceRequest;
import com.amazonaws.services.rds.model.ListTagsForResourceResult;
import com.amazonaws.services.rds.model.RemoveTagsFromResourceRequest;
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
import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "rds",
        canonicalName = "Rds",
        title = "Amazon RDS",
        description = "Managed Relational Database Service for MySQL, PostgreSQL, Oracle, SQL Server, and MariaDB",
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
public final class RdsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbInstances.list",
            path = "{region}/db-instances"
    )
    public DbInstancesResponse dbInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBInstancesRequest.class, DbInstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDBInstancesResult result = client.describeDBInstances(request.withMarker(page));
            response.setDbInstances(result.getDBInstances());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbParameterGroups.list",
            path = "{region}/db-parameter-groups"
    )
    public DbParameterGroupsResponse dbParameterGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBParameterGroupsRequest.class, DbParameterGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDBParameterGroupsResult result = client.describeDBParameterGroups(request.withMarker(page));
            response.setDbParameterGroups(result.getDBParameterGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbSecurityGroups.list",
            path = "{region}/db-security-groups"
    )
    public DbSecurityGroupsResponse dbSecurityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBSecurityGroupsRequest.class, DbSecurityGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDBSecurityGroupsResult result = client.describeDBSecurityGroups(request.withMarker(page));
            response.setDbSecurityGroups(result.getDBSecurityGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbSubnetGroups.list",
            path = "{region}/db-subnet-groups"
    )
    public DbSubnetGroupsResponse dbSubnetGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBSubnetGroupsRequest.class, DbSubnetGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDBSubnetGroupsResult result = client.describeDBSubnetGroups(request.withMarker(page));
            response.setDbSubnetGroups(result.getDBSubnetGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbSnapshots.list",
            path = "{region}/db-snapshots"
    )
    public DbSnapshotsResponse dbSnapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBSnapshotsRequest.class, DbSnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDBSnapshotsResult result = client.describeDBSnapshots(request.withMarker(page));
            response.setDbSnapshots(result.getDBSnapshots());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbSnapshots.attributes.list",
            path = "{region}/dbSnapshots/{dbSnapshotId}/attributes"
    )
    public DbSnapshotAttributesResponse dbSnapshotsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("dbSnapshotId") final String dbSnapshotId
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBSnapshotAttributesRequest.class, DbSnapshotAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final DBSnapshotAttributesResult result = client.describeDBSnapshotAttributes(request.withDBSnapshotIdentifier(dbSnapshotId));
            response.setDbSnapshotAttributes(result.getDBSnapshotAttributes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbClusters.list",
            path = "{region}/db-clusters"
    )
    public DbClustersResponse dbClustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBClustersRequest.class, DbClustersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDBClustersResult result = client.describeDBClusters(request.withMarker(page));
            response.setDbClusters(result.getDBClusters());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbClusterSnapshots.list",
            path = "{region}/db-cluster-snapshots"
    )
    public DbClusterSnapshotsResponse dbClusterSnapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBClusterSnapshotsRequest.class, DbClusterSnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDBClusterSnapshotsResult result = client.describeDBClusterSnapshots(request.withMarker(page));
            response.setDbClusterSnapshots(result.getDBClusterSnapshots());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbClusterSnapshots.attributes.list",
            path = "{region}/dbClusterSnapshots/{dbClusterSnapshotId}/attributes"
    )
    public DbClusterSnapshotAttributesResponse dbClusterSnapshotsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("dbClusterSnapshotId") final String dbClusterSnapshotId
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBClusterSnapshotAttributesRequest.class, DbClusterSnapshotAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final DBClusterSnapshotAttributesResult result = client.describeDBClusterSnapshotAttributes(request.withDBClusterSnapshotIdentifier(dbClusterSnapshotId));
            response.setDbClusterSnapshotAttributes(result.getDBClusterSnapshotAttributes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbClusterParameterGroups.list",
            path = "{region}/db-cluster-parameter-groups"
    )
    public DbClusterParameterGroupsResponse dbClusterParameterGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeDBClusterParameterGroupsRequest.class, DbClusterParameterGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDBClusterParameterGroupsResult result = client.describeDBClusterParameterGroups(request.withMarker(page));
            response.setDbClusterParameterGroups(result.getDBClusterParameterGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "reservedDbInstances.list",
            path = "{region}/reserved-db-instances"
    )
    public ReservedDbInstancesResponse reservedDbInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeReservedDBInstancesRequest.class, ReservedDbInstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeReservedDBInstancesResult result = client.describeReservedDBInstances(request.withMarker(page));
            response.setReservedDbInstances(result.getReservedDBInstances());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "events.list",
            path = "{region}/events"
    )
    public EventsResponse dbEventsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("durationInMinutes") @Nullable final Integer durationInMinutes,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeEventsRequest.class, EventsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventsResult result = client.describeEvents(request.withDuration(durationInMinutes).withMarker(page));
            response.setEvents(result.getEvents());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accountAttributes.list",
            path = "{region}/account-attributes"
    )
    public AccountQuotasResponse accountAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return RdsCaller.get(DescribeAccountAttributesRequest.class, AccountQuotasResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAccountAttributesResult result = client.describeAccountAttributes(request);
            response.setAccountQuotas(result.getAccountQuotas());
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
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        return RdsCaller.get(ListTagsForResourceRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsForResourceResult result = client.listTagsForResource(request.withResourceName(arn));
            response.setTags(result.getTagList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.add",
            path = "{region}/tags/add"
    )
    public AmazonResponse tagsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return RdsCaller.get(AddTagsToResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            final List<com.amazonaws.services.rds.model.Tag> tags = Lists.newArrayList();
            for (final Map.Entry<String, String> e : tagsRequest.getTags().entrySet()) {
                tags.add(new com.amazonaws.services.rds.model.Tag().withKey(e.getKey()).withValue(e.getValue()));
            }

            client.addTagsToResource(
                    request.withTags(tags)
                            .withResourceName(tagsRequest.getResourceArn())
            );
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.remove",
            path = "{region}/tags/remove"
    )
    public AmazonResponse tagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final RemoveTagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return RdsCaller.get(RemoveTagsFromResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            client.removeTagsFromResource(
                    request.withTagKeys(tagsRequest.getTagKeys())
                            .withResourceName(tagsRequest.getResourceArn())
            );
        });
    }
}
