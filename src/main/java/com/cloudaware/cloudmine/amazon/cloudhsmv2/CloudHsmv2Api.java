package com.cloudaware.cloudmine.amazon.cloudhsmv2;

import com.amazonaws.services.cloudhsmv2.model.DescribeBackupsRequest;
import com.amazonaws.services.cloudhsmv2.model.DescribeBackupsResult;
import com.amazonaws.services.cloudhsmv2.model.DescribeClustersRequest;
import com.amazonaws.services.cloudhsmv2.model.DescribeClustersResult;
import com.amazonaws.services.cloudhsmv2.model.ListTagsRequest;
import com.amazonaws.services.cloudhsmv2.model.ListTagsResult;
import com.amazonaws.services.cloudhsmv2.model.TagResourceRequest;
import com.amazonaws.services.cloudhsmv2.model.UntagResourceRequest;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "cloudhsmv2",
        canonicalName = "CloudHSM v2",
        title = "Amazon CloudHSM v2",
        description = "Hardware Security Modules in Cloud",
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
public final class CloudHsmv2Api {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "backups.list",
            path = "{region}/backups"
    )
    public BackupsListResponse backupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudHsmv2Caller.get(DescribeBackupsRequest.class, BackupsListResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeBackupsResult result = client.describeBackups(
                    request.withNextToken(page)
            );
            response.setBackups(result.getBackups());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/clusters"
    )
    public ClustersListResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudHsmv2Caller.get(DescribeClustersRequest.class, ClustersListResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClustersResult result = client.describeClusters(
                    request.withNextToken(page)
            );
            response.setClusters(result.getClusters());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.list",
            path = "{region}/tags"
    )
    public TagsListResponse tagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceId") final String resourceId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudHsmv2Caller.get(ListTagsRequest.class, TagsListResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsResult result = client.listTags(
                    request.withResourceId(resourceId).withNextToken(page)
            );
            response.setTags(result.getTagList());
            response.setNextPage(result.getNextToken());
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
            final TagResourceRequest request
    ) throws AmazonUnparsedException {
        return CloudHsmv2Caller.get(TagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, tagResourceRequest, response) -> {
            tagResourceRequest.withResourceId(request.getResourceId());
            tagResourceRequest.withTagList(request.getTagList());
            client.tagResource(tagResourceRequest);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "tags.remove",
            path = "{region}/tags/remove"
    )
    public AmazonResponse tagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final UntagResourceRequest request
    ) throws AmazonUnparsedException {
        return CloudHsmv2Caller.get(UntagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, untagResourceRequest, response) -> {
            untagResourceRequest.withResourceId(request.getResourceId());
            untagResourceRequest.withTagKeyList(request.getTagKeyList());
            client.untagResource(untagResourceRequest);
        });
    }
}
