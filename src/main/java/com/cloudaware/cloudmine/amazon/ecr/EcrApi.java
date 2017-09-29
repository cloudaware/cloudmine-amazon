package com.cloudaware.cloudmine.amazon.ecr;

import com.amazonaws.services.ecr.model.DescribeImagesRequest;
import com.amazonaws.services.ecr.model.DescribeImagesResult;
import com.amazonaws.services.ecr.model.DescribeRepositoriesRequest;
import com.amazonaws.services.ecr.model.DescribeRepositoriesResult;
import com.amazonaws.services.ecr.model.GetRepositoryPolicyRequest;
import com.amazonaws.services.ecr.model.GetRepositoryPolicyResult;
import com.amazonaws.services.ecr.model.ListImagesRequest;
import com.amazonaws.services.ecr.model.ListImagesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "ecr",
        canonicalName = "Ecr",
        title = "Amazon EC2 Container Registry",
        description = "Fully-managed Docker container registry that makes it easy for developers to store, manage, and deploy Docker container images",
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
public final class EcrApi {

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
        return EcrCaller.get(DescribeRepositoriesRequest.class, RepositoriesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeRepositoriesResult result = client.describeRepositories(request.withNextToken(page));
            response.setRepositories(result.getRepositories());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "repositories.policy.get",
            path = "{region}/repositories/{repositoryName}/policy"
    )
    public RepositoryPolicyResponse repositoriesGetPolicy(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("repositoryName") final String repositoryName
    ) throws AmazonUnparsedException {
        return EcrCaller.get(GetRepositoryPolicyRequest.class, RepositoryPolicyResponse.class, credentials, region).execute((client, request, response) -> {
            final GetRepositoryPolicyResult result = client.getRepositoryPolicy(request.withRepositoryName(repositoryName));
            response.setPolicy(result.getPolicyText());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "repositories.imageIdentifiers.list",
            path = "{region}/repositories/{repositoryName}/image-identifiers"
    )
    public ImageIdentifiersResponse repositoriesImageIdentifiersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("repositoryName") final String repositoryName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EcrCaller.get(ListImagesRequest.class, ImageIdentifiersResponse.class, credentials, region).execute((client, request, response) -> {
            final ListImagesResult result = client.listImages(request.withNextToken(page).withRepositoryName(repositoryName));
            response.setImageIdentifiers(result.getImageIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "repositories.imageDetails.list",
            path = "{region}/repositories/{repositoryName}/image-details"
    )
    public ImageDetailsResponse repositoriesImageDetailsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("repositoryName") final String repositoryName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EcrCaller.get(DescribeImagesRequest.class, ImageDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeImagesResult result = client.describeImages(request.withNextToken(page).withRepositoryName(repositoryName));
            response.setImageDetails(result.getImageDetails());
            response.setNextPage(result.getNextToken());
        });
    }

}
