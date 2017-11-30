package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.GetApiKeysRequest;
import com.amazonaws.services.apigateway.model.GetApiKeysResult;
import com.amazonaws.services.apigateway.model.GetAuthorizersRequest;
import com.amazonaws.services.apigateway.model.GetAuthorizersResult;
import com.amazonaws.services.apigateway.model.GetClientCertificatesRequest;
import com.amazonaws.services.apigateway.model.GetClientCertificatesResult;
import com.amazonaws.services.apigateway.model.GetDeploymentsRequest;
import com.amazonaws.services.apigateway.model.GetDeploymentsResult;
import com.amazonaws.services.apigateway.model.GetDocumentationPartsRequest;
import com.amazonaws.services.apigateway.model.GetDocumentationPartsResult;
import com.amazonaws.services.apigateway.model.GetDomainNamesRequest;
import com.amazonaws.services.apigateway.model.GetDomainNamesResult;
import com.amazonaws.services.apigateway.model.GetModelsRequest;
import com.amazonaws.services.apigateway.model.GetModelsResult;
import com.amazonaws.services.apigateway.model.GetResourcesRequest;
import com.amazonaws.services.apigateway.model.GetResourcesResult;
import com.amazonaws.services.apigateway.model.GetRestApisRequest;
import com.amazonaws.services.apigateway.model.GetRestApisResult;
import com.amazonaws.services.apigateway.model.GetStagesRequest;
import com.amazonaws.services.apigateway.model.GetStagesResult;
import com.amazonaws.services.apigateway.model.GetUsagePlanKeysRequest;
import com.amazonaws.services.apigateway.model.GetUsagePlanKeysResult;
import com.amazonaws.services.apigateway.model.GetUsagePlansRequest;
import com.amazonaws.services.apigateway.model.GetUsagePlansResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "apigateway",
        canonicalName = "ApiGateway",
        title = "Amazon API Gateway",
        description = "Amazon API Gateway is a fully managed service that makes it easy for developers to create, publish, maintain, monitor, and secure APIs at any scale.",
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
public final class ApiGatewayApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "apiKeys.list",
            path = "{region}/api-keys"
    )
    public ApiKeysResponse apiKeysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetApiKeysRequest.class, ApiKeysResponse.class, credentials, region).execute((client, request, response) -> {
            final GetApiKeysResult result = client.getApiKeys(request.withPosition(page));
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clientCertificates.list",
            path = "{region}/client-certificates"
    )
    public ClientCertificatesResponse clientCertificatesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetClientCertificatesRequest.class, ClientCertificatesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetClientCertificatesResult result = client.getClientCertificates(request.withPosition(page));
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domainNames.list",
            path = "{region}/domain-names"
    )
    public DomainNamesResponse domainNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetDomainNamesRequest.class, DomainNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDomainNamesResult result = client.getDomainNames(request.withPosition(page));
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "restApis.list",
            path = "{region}/rest-apis"
    )
    public RestApisResponse restApisList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetRestApisRequest.class, RestApisResponse.class, credentials, region).execute((client, request, response) -> {
            final GetRestApisResult result = client.getRestApis(request.withPosition(page));
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "restApis.resources.list",
            path = "{region}/rest-apis/{restApiId}/resources"
    )
    public ResourcesResponse restApisResourcesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("restApiId") final String restApiId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetResourcesRequest.class, ResourcesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetResourcesResult result = client.getResources(
                    request
                            .withRestApiId(restApiId)
                            .withPosition(page)
            );
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "restApis.deployments.list",
            path = "{region}/rest-apis/{restApiId}/deployments"
    )
    public DeploymentsResponse restApisDeploymentsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("restApiId") final String restApiId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetDeploymentsRequest.class, DeploymentsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDeploymentsResult result = client.getDeployments(
                    request
                            .withRestApiId(restApiId)
                            .withPosition(page)
            );
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "restApis.stages.list",
            path = "{region}/rest-apis/{restApiId}/stages"
    )
    public StagesResponse restApisStagesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("restApiId") final String restApiId
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetStagesRequest.class, StagesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetStagesResult result = client.getStages(request.withRestApiId(restApiId));
            response.setItems(result.getItem());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "restApis.models.list",
            path = "{region}/rest-apis/{restApiId}/models"
    )
    public ModelsResponse restApisModelsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("restApiId") final String restApiId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetModelsRequest.class, ModelsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetModelsResult result = client.getModels(
                    request
                            .withRestApiId(restApiId)
                            .withPosition(page)
            );
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "restApis.authorizers.list",
            path = "{region}/rest-apis/{restApiId}/authorizers"
    )
    public AuthorizersResponse restApisAuthorizersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("restApiId") final String restApiId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetAuthorizersRequest.class, AuthorizersResponse.class, credentials, region).execute((client, request, response) -> {
            final GetAuthorizersResult result = client.getAuthorizers(
                    request
                            .withRestApiId(restApiId)
                            .withPosition(page)
            );
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "restApis.documentationParts.list",
            path = "{region}/rest-apis/{restApiId}/documentation-parts"
    )
    public DocumentationPartsResponse restApisDocumentationPartsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("restApiId") final String restApiId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetDocumentationPartsRequest.class, DocumentationPartsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDocumentationPartsResult result = client.getDocumentationParts(
                    request
                            .withRestApiId(restApiId)
                            .withPosition(page)
            );
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "usagePlans.list",
            path = "{region}/usage-plans"
    )
    public UsagePlansResponse usagePlansList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetUsagePlansRequest.class, UsagePlansResponse.class, credentials, region).execute((client, request, response) -> {
            final GetUsagePlansResult result = client.getUsagePlans(request.withPosition(page));
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "usagePlans.usagePlanKeys.list",
            path = "{region}/usage-plans/{usagePlanId}/usage-plan-keys"
    )
    public UsagePlanKeysResponse usagePlansUsagePlanKeysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("usagePlanId") final String usagePlanId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApiGatewayCaller.get(GetUsagePlanKeysRequest.class, UsagePlanKeysResponse.class, credentials, region).execute((client, request, response) -> {
            final GetUsagePlanKeysResult result = client.getUsagePlanKeys(
                    request
                            .withUsagePlanId(usagePlanId)
                            .withPosition(page)
            );
            response.setItems(result.getItems());
            response.setNextPage(result.getPosition());
        });
    }
}
