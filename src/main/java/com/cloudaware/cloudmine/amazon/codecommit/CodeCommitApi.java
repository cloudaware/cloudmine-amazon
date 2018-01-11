package com.cloudaware.cloudmine.amazon.codecommit;

import com.amazonaws.services.codecommit.model.GetBranchRequest;
import com.amazonaws.services.codecommit.model.GetBranchResult;
import com.amazonaws.services.codecommit.model.GetRepositoryRequest;
import com.amazonaws.services.codecommit.model.GetRepositoryResult;
import com.amazonaws.services.codecommit.model.GetRepositoryTriggersRequest;
import com.amazonaws.services.codecommit.model.GetRepositoryTriggersResult;
import com.amazonaws.services.codecommit.model.ListBranchesRequest;
import com.amazonaws.services.codecommit.model.ListBranchesResult;
import com.amazonaws.services.codecommit.model.ListRepositoriesRequest;
import com.amazonaws.services.codecommit.model.ListRepositoriesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "codecommit",
        canonicalName = "CodeCommit",
        title = "AWS CodeCommit",
        description = "Securely store and version your project with AWS CodeCommit",
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
public final class CodeCommitApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "repositories.list",
            path = "{region}/repositories"
    )
    public RepositoriesResponse repositoriesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeCommitCaller.get(ListRepositoriesRequest.class, RepositoriesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListRepositoriesResult result = client.listRepositories(request.withNextToken(page));
            response.setRepositories(result.getRepositories());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "repositories.get",
            path = "{region}/repositories/{repositoryName}"
    )
    public RepositoryResponse repositoriesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("repositoryName") final String repositoryName
    ) throws AmazonUnparsedException {
        return CodeCommitCaller.get(GetRepositoryRequest.class, RepositoryResponse.class, credentials, region).execute((client, request, response) -> {
            final GetRepositoryResult result = client.getRepository(request.withRepositoryName(repositoryName));
            response.setRepository(result.getRepositoryMetadata());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "repositories.branches.list",
            path = "{region}/repositories/{repositoryName}/branches"
    )
    public BranchesResponse repositoriesBranchesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("repositoryName") final String repositoryName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeCommitCaller.get(ListBranchesRequest.class, BranchesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListBranchesResult result = client.listBranches(
                    request
                            .withRepositoryName(repositoryName)
                            .withNextToken(page)
            );
            response.setBranches(result.getBranches());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "repositories.branches.get",
            path = "{region}/repositories/{repositoryName}/branches/{branchName}"
    )
    public BranchResponse repositoriesBranchesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("repositoryName") final String repositoryName,
            @Named("branchName") final String branchName
    ) throws AmazonUnparsedException {
        return CodeCommitCaller.get(GetBranchRequest.class, BranchResponse.class, credentials, region).execute((client, request, response) -> {
            final GetBranchResult result = client.getBranch(
                    request
                            .withRepositoryName(repositoryName)
                            .withBranchName(branchName)
            );
            response.setBranch(result.getBranch());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "repositories.repositoryTriggers.get",
            path = "{region}/repositories/{repositoryName}/repository-triggers"
    )
    public RepositoryTriggersResponse repositoriesRepositoryTriggersGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("repositoryName") final String repositoryName
    ) throws AmazonUnparsedException {
        return CodeCommitCaller.get(GetRepositoryTriggersRequest.class, RepositoryTriggersResponse.class, credentials, region).execute((client, request, response) -> {
            final GetRepositoryTriggersResult result = client.getRepositoryTriggers(request.withRepositoryName(repositoryName));
            response.setConfigurationId(result.getConfigurationId());
            response.setTriggers(result.getTriggers());
        });
    }
}
