package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.model.DescribeWorkspaceBundlesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceBundlesResult;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceDirectoriesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceDirectoriesResult;
import com.amazonaws.services.workspaces.model.DescribeWorkspacesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspacesResult;
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
        name = "workspaces",
        canonicalName = "Workspaces",
        title = "Amazon WorkSpaces",
        description = "Desktop Computing Service",
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
public final class WorkspacesApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "workspaces.list",
            path = "{region}/workspaces"
    )
    public WorkspacesResponse workspacesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeWorkspacesRequest.class, WorkspacesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeWorkspacesResult result = client.describeWorkspaces(request.withNextToken(page));
            response.setWorkspaces(result.getWorkspaces());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "workspaceDirectories.list",
            path = "{region}/workspace-directories"
    )
    public WorkspaceDirectoriesResponse workspaceDirectoriesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeWorkspaceDirectoriesRequest.class, WorkspaceDirectoriesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeWorkspaceDirectoriesResult result = client.describeWorkspaceDirectories(request.withNextToken(page));
            response.setWorkspaceDirectories(result.getDirectories());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "workspaceBundles.list",
            path = "{region}/workspace-bundles"
    )
    public WorkspaceBundlesResponse workspaceBundlesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeWorkspaceBundlesRequest.class, WorkspaceBundlesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeWorkspaceBundlesResult result = client.describeWorkspaceBundles(request.withNextToken(page));
            response.setWorkspaceBundles(result.getBundles());
            response.setNextPage(result.getNextToken());
        });
    }
}
