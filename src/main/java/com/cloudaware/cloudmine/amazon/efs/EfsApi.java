package com.cloudaware.cloudmine.amazon.efs;

import com.amazonaws.services.elasticfilesystem.model.CreateTagsRequest;
import com.amazonaws.services.elasticfilesystem.model.DeleteTagsRequest;
import com.amazonaws.services.elasticfilesystem.model.DescribeFileSystemsRequest;
import com.amazonaws.services.elasticfilesystem.model.DescribeFileSystemsResult;
import com.amazonaws.services.elasticfilesystem.model.DescribeMountTargetSecurityGroupsRequest;
import com.amazonaws.services.elasticfilesystem.model.DescribeMountTargetSecurityGroupsResult;
import com.amazonaws.services.elasticfilesystem.model.DescribeMountTargetsRequest;
import com.amazonaws.services.elasticfilesystem.model.DescribeMountTargetsResult;
import com.amazonaws.services.elasticfilesystem.model.DescribeTagsRequest;
import com.amazonaws.services.elasticfilesystem.model.DescribeTagsResult;
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
        name = "efs",
        canonicalName = "Efs",
        title = "Amazon Elastic File System",
        description = "Simple, scalable, and reliable file storage for the AWS Cloud",
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
public final class EfsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "fileSystems.list",
            path = "{region}/file-systems"
    )
    public FileSystemsResponse fileSystemsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("creationToken") @Nullable final String creationToken,
            @Named("fileSystemId") @Nullable final String fileSystemId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EfsCaller.get(DescribeFileSystemsRequest.class, FileSystemsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeFileSystemsResult result = client.describeFileSystems(
                    request
                            .withCreationToken(creationToken)
                            .withFileSystemId(fileSystemId)
                            .withMarker(page)
            );
            response.setFileSystems(result.getFileSystems());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "mountTargets.list",
            path = "{region}/mount-targets"
    )
    public MountTargetsResponse mountTargetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("fileSystemId") @Nullable final String fileSystemId,
            @Named("mountTargetId") @Nullable final String mountTargetId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EfsCaller.get(DescribeMountTargetsRequest.class, MountTargetsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeMountTargetsResult result = client.describeMountTargets(
                    request
                            .withFileSystemId(fileSystemId)
                            .withMountTargetId(mountTargetId)
                            .withMarker(page)
            );
            response.setMountTargets(result.getMountTargets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "mountTargets.securityGroups.list",
            path = "{region}/mount-targets/{mountTargetId}/security-groups"
    )
    public SecurityGroupsResponse securityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("mountTargetId") final String mountTargetId
    ) throws AmazonUnparsedException {
        return EfsCaller.get(DescribeMountTargetSecurityGroupsRequest.class, SecurityGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeMountTargetSecurityGroupsResult result = client.describeMountTargetSecurityGroups(
                    request
                            .withMountTargetId(mountTargetId)
            );
            response.setSecurityGroups(result.getSecurityGroups());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "fileSystems.tags.list",
            path = "{region}/file-systems/{fileSystemId}/tags"
    )
    public TagsResponse fileSystemTagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("fileSystemId") final String fileSystemId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EfsCaller.get(DescribeTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTagsResult result = client.describeTags(
                    request
                            .withFileSystemId(fileSystemId)
                            .withMarker(page)
            );
            response.setTags(result.getTags());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "fileSystems.tags.create",
            path = "{region}/file-systems/{fileSystemId}/tags"
    )
    public AmazonResponse fileSystemagsCreate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("fileSystemId") final String fileSystemId,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return EfsCaller.get(CreateTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.createTags(r.withFileSystemId(fileSystemId).withTags(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "fileSystems.tags.delete",
            path = "{region}/file-systems/{fileSystemId}/tags"
    )
    public AmazonResponse fileSystemTagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("fileSystemId") final String fileSystemId,
            @Named("tagKey") final List<String> tagKeys
    ) throws AmazonUnparsedException {
        return EfsCaller.get(DeleteTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.deleteTags(r.withFileSystemId(fileSystemId).withTagKeys(tagKeys));
        });
    }
}
