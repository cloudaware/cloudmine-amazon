package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.GetCloudFrontOriginAccessIdentityRequest;
import com.amazonaws.services.cloudfront.model.GetCloudFrontOriginAccessIdentityResult;
import com.amazonaws.services.cloudfront.model.GetDistributionRequest;
import com.amazonaws.services.cloudfront.model.GetDistributionResult;
import com.amazonaws.services.cloudfront.model.GetInvalidationRequest;
import com.amazonaws.services.cloudfront.model.GetInvalidationResult;
import com.amazonaws.services.cloudfront.model.GetStreamingDistributionRequest;
import com.amazonaws.services.cloudfront.model.GetStreamingDistributionResult;
import com.amazonaws.services.cloudfront.model.ListCloudFrontOriginAccessIdentitiesRequest;
import com.amazonaws.services.cloudfront.model.ListCloudFrontOriginAccessIdentitiesResult;
import com.amazonaws.services.cloudfront.model.ListDistributionsRequest;
import com.amazonaws.services.cloudfront.model.ListDistributionsResult;
import com.amazonaws.services.cloudfront.model.ListInvalidationsRequest;
import com.amazonaws.services.cloudfront.model.ListInvalidationsResult;
import com.amazonaws.services.cloudfront.model.ListStreamingDistributionsRequest;
import com.amazonaws.services.cloudfront.model.ListStreamingDistributionsResult;
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
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "cloudfront",
        canonicalName = "CloudFront",
        title = "Amazon CloudFront",
        description = "Global Content Delivery Network",
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
public final class CloudFrontApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "distributions.list",
            path = "distributions"
    )
    public DistributionsResponse distributionsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudFrontCaller.get(ListDistributionsRequest.class, DistributionsResponse.class, credentials).execute((client, request, response) -> {
            final ListDistributionsResult result = client.listDistributions(
                    request
                            .withMarker(page)
            );
            response.setDistributions(result.getDistributionList().getItems());
            response.setNextPage(result.getDistributionList().getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "distributions.get",
            path = "distributions/{id}"
    )
    public DistributionResponse distributionsGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return CloudFrontCaller.get(GetDistributionRequest.class, DistributionResponse.class, credentials).execute((client, request, response) -> {
            final GetDistributionResult result = client.getDistribution(request.withId(id));
            response.setDistribution(result.getDistribution());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "streamingDistributions.list",
            path = "streaming-distributions"
    )
    public StreamingDistributionsResponse streamingDistributionsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudFrontCaller.get(ListStreamingDistributionsRequest.class, StreamingDistributionsResponse.class, credentials).execute((client, request, response) -> {
            final ListStreamingDistributionsResult result = client.listStreamingDistributions(request.withMarker(page));
            response.setStreamingDistributions(result.getStreamingDistributionList().getItems());
            response.setNextPage(result.getStreamingDistributionList().getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "streamingDistributions.get",
            path = "streaming-distributions/{id}"
    )
    public StreamingDistributionResponse streamingDistributionsGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return CloudFrontCaller.get(GetStreamingDistributionRequest.class, StreamingDistributionResponse.class, credentials).execute((client, request, response) -> {
            final GetStreamingDistributionResult result = client.getStreamingDistribution(request.withId(id));
            response.setStreamingDistribution(result.getStreamingDistribution());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "distributions.invalidations.list",
            path = "distributions/{distributionId}/invalidations"
    )
    public InvalidationsResponse distributionsInvalidationsList(
            @Named("credentials") final String credentials,
            @Named("distributionId") final String distributionId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudFrontCaller.get(ListInvalidationsRequest.class, InvalidationsResponse.class, credentials).execute((client, request, response) -> {
            final ListInvalidationsResult result = client.listInvalidations(
                    request
                            .withDistributionId(distributionId)
                            .withMarker(page)
            );
            response.setInvalidationSummaries(result.getInvalidationList().getItems());
            response.setNextPage(result.getInvalidationList().getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "distributions.invalidations.get",
            path = "distributions/{distributionId}/invalidations/{id}"
    )
    public InvalidationResponse distributionsInvalidationsGet(
            @Named("credentials") final String credentials,
            @Named("distributionId") final String distributionId,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return CloudFrontCaller.get(GetInvalidationRequest.class, InvalidationResponse.class, credentials).execute((client, request, response) -> {
            final GetInvalidationResult result = client.getInvalidation(
                    request
                            .withDistributionId(distributionId)
                            .withId(id)
            );
            response.setInvalidation(result.getInvalidation());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "originAccessIdentities.list",
            path = "origin-access-identities"
    )
    public OriginAccessIdentitiesResponse originAccessIdentitiesList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudFrontCaller.get(ListCloudFrontOriginAccessIdentitiesRequest.class, OriginAccessIdentitiesResponse.class, credentials).execute((client, request, response) -> {
            final ListCloudFrontOriginAccessIdentitiesResult result = client.listCloudFrontOriginAccessIdentities(request.withMarker(page));
            response.setOriginAccessIdentitySummaries(result.getCloudFrontOriginAccessIdentityList().getItems());
            response.setNextPage(result.getCloudFrontOriginAccessIdentityList().getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "originAccessIdentities.get",
            path = "origin-access-identities/{id}"
    )
    public OriginAccessIdentityResponse originAccessIdentitiesGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return CloudFrontCaller.get(GetCloudFrontOriginAccessIdentityRequest.class, OriginAccessIdentityResponse.class, credentials).execute((client, request, response) -> {
            final GetCloudFrontOriginAccessIdentityResult result = client.getCloudFrontOriginAccessIdentity(request.withId(id));
            response.setOriginAccessIdentity(result.getCloudFrontOriginAccessIdentity());
        });
    }
}
