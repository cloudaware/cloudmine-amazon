package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.DescribeConditionalForwardersRequest;
import com.amazonaws.services.directory.model.DescribeConditionalForwardersResult;
import com.amazonaws.services.directory.model.DescribeDirectoriesRequest;
import com.amazonaws.services.directory.model.DescribeDirectoriesResult;
import com.amazonaws.services.directory.model.DescribeEventTopicsRequest;
import com.amazonaws.services.directory.model.DescribeEventTopicsResult;
import com.amazonaws.services.directory.model.DescribeSnapshotsRequest;
import com.amazonaws.services.directory.model.DescribeSnapshotsResult;
import com.amazonaws.services.directory.model.DescribeTrustsRequest;
import com.amazonaws.services.directory.model.DescribeTrustsResult;
import com.amazonaws.services.directory.model.GetDirectoryLimitsRequest;
import com.amazonaws.services.directory.model.GetDirectoryLimitsResult;
import com.amazonaws.services.directory.model.GetSnapshotLimitsRequest;
import com.amazonaws.services.directory.model.GetSnapshotLimitsResult;
import com.amazonaws.services.directory.model.ListIpRoutesRequest;
import com.amazonaws.services.directory.model.ListIpRoutesResult;
import com.amazonaws.services.directory.model.ListSchemaExtensionsRequest;
import com.amazonaws.services.directory.model.ListSchemaExtensionsResult;
import com.amazonaws.services.directory.model.ListTagsForResourceRequest;
import com.amazonaws.services.directory.model.ListTagsForResourceResult;
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
        name = "directoryservice",
        canonicalName = "DirectoryService",
        title = "AWS Directory Service",
        description = "Managed Microsoft Active Directory in the AWS Cloud",
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

public final class DirectoryServiceApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.list",
            path = "{region}/directories"
    )
    public DirectoriesResponse directoriesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") @Nullable final List<String> directoryIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(DescribeDirectoriesRequest.class, DirectoriesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDirectoriesResult result = client.describeDirectories(
                    request
                            .withDirectoryIds(directoryIds)
                            .withNextToken(page)
            );
            response.setDirectories(result.getDirectoryDescriptions());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.conditionalForwarders.list",
            path = "{region}/directories/{directoryId}/conditional-forwarders"
    )
    public ConditionalForwardersResponse directoriesConditionalForwardersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("remoteDomainName") @Nullable final List<String> remoteDomainNames
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(DescribeConditionalForwardersRequest.class, ConditionalForwardersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConditionalForwardersResult result = client.describeConditionalForwarders(
                    request
                            .withDirectoryId(directoryId)
                            .withRemoteDomainNames(remoteDomainNames)
            );
            response.setConditionalForwarders(result.getConditionalForwarders());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.eventTopics.list",
            path = "{region}/directories/{directoryId}/event-topics"
    )
    public EventTopicsResponse directoriesEventTopicsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("topicName") @Nullable final List<String> topicNames
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(DescribeEventTopicsRequest.class, EventTopicsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventTopicsResult result = client.describeEventTopics(
                    request
                            .withDirectoryId(directoryId)
                            .withTopicNames(topicNames)
            );
            response.setEventTopics(result.getEventTopics());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.snapshots.list",
            path = "{region}/directories/{directoryId}/snapshots"
    )
    public SnapshotsResponse directoriesSnapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("snapshotId") @Nullable final List<String> snapshotIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(DescribeSnapshotsRequest.class, SnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSnapshotsResult result = client.describeSnapshots(
                    request
                            .withDirectoryId(directoryId)
                            .withSnapshotIds(snapshotIds)
                            .withNextToken(page)
            );
            response.setSnapshots(result.getSnapshots());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.trusts.list",
            path = "{region}/directories/{directoryId}/trusts"
    )
    public TrustsResponse directoriesTrustsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("trustId") @Nullable final List<String> trustIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(DescribeTrustsRequest.class, TrustsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTrustsResult result = client.describeTrusts(
                    request
                            .withDirectoryId(directoryId)
                            .withTrustIds(trustIds)
                            .withNextToken(page)
            );
            response.setTrusts(result.getTrusts());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directoryLimits.get",
            path = "{region}/directory-limits"
    )
    public DirectoryLimitsResponse directoryLimitsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(GetDirectoryLimitsRequest.class, DirectoryLimitsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDirectoryLimitsResult result = client.getDirectoryLimits(request);
            response.setDirectoryLimits(result.getDirectoryLimits());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.snapshotLimits.get",
            path = "{region}/directories/{directoryId}/snapshot-limits"
    )
    public SnapshotLimitsResponse directoriesSnapshotLimitsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(GetSnapshotLimitsRequest.class, SnapshotLimitsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetSnapshotLimitsResult result = client.getSnapshotLimits(request.withDirectoryId(directoryId));
            response.setSnapshotLimits(result.getSnapshotLimits());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.ipRoutes.list",
            path = "{region}/directories/{directoryId}/ip-routes"
    )
    public IpRoutesResponse directoriesIpRoutesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(ListIpRoutesRequest.class, IpRoutesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListIpRoutesResult result = client.listIpRoutes(
                    request
                            .withDirectoryId(directoryId)
                            .withNextToken(page)
            );
            response.setIpRoutes(result.getIpRoutesInfo());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.schemaExtensions.list",
            path = "{region}/directories/{directoryId}/schema-extensions"
    )
    public SchemaExtensionsResponse directoriesSchemaExtensionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(ListSchemaExtensionsRequest.class, SchemaExtensionsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListSchemaExtensionsResult result = client.listSchemaExtensions(
                    request
                            .withDirectoryId(directoryId)
                            .withNextToken(page)
            );
            response.setSchemaExtensions(result.getSchemaExtensionsInfo());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.tags.get",
            path = "{region}/directories/{directoryId}/tags"
    )
    public TagsResponse directoriesTagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DirectoryServiceCaller.get(ListTagsForResourceRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsForResourceResult result = client.listTagsForResource(
                    request
                            .withResourceId(directoryId)
                            .withNextToken(page)
            );
            response.setTags(result.getTags());
            response.setNextPage(result.getNextToken());
        });
    }
}
