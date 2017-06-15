package com.cloudaware.cloudmine.amazon.lambda;

import com.amazonaws.services.lambda.model.GetFunctionRequest;
import com.amazonaws.services.lambda.model.GetFunctionResult;
import com.amazonaws.services.lambda.model.GetPolicyRequest;
import com.amazonaws.services.lambda.model.GetPolicyResult;
import com.amazonaws.services.lambda.model.ListAliasesRequest;
import com.amazonaws.services.lambda.model.ListAliasesResult;
import com.amazonaws.services.lambda.model.ListEventSourceMappingsRequest;
import com.amazonaws.services.lambda.model.ListEventSourceMappingsResult;
import com.amazonaws.services.lambda.model.ListFunctionsRequest;
import com.amazonaws.services.lambda.model.ListFunctionsResult;
import com.amazonaws.services.lambda.model.ListVersionsByFunctionRequest;
import com.amazonaws.services.lambda.model.ListVersionsByFunctionResult;
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
        name = "lambda",
        canonicalName = "Lambda",
        title = "AWS Lambda",
        description = "Run Your Code in Response to Events",
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
public final class LambdaApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.list",
            path = "{region}/functions"
    )
    public FunctionsResponse functionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LambdaCaller.get(ListFunctionsRequest.class, FunctionsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListFunctionsResult result = client.listFunctions(request.withMarker(page));
            response.setFunctions(result.getFunctions());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.get",
            path = "{region}/functions/FUNCTION_NAME"
    )
    public FunctionResponse functionsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("functionName") final String functionName
    ) throws AmazonUnparsedException {
        return LambdaCaller.get(GetFunctionRequest.class, FunctionResponse.class, credentials, region).execute((client, request, response) -> {
            final GetFunctionResult result = client.getFunction(request.withFunctionName(functionName));
            response.setConfiguration(result.getConfiguration());
            response.setCode(result.getCode());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.versions.list",
            path = "{region}/functions/FUNCTION_NAME/versions"
    )
    public VersionsResponse functionsVersionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("functionName") final String functionName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LambdaCaller.get(ListVersionsByFunctionRequest.class, VersionsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListVersionsByFunctionResult result = client.listVersionsByFunction(
                    request
                            .withFunctionName(functionName)
                            .withMarker(page)
            );
            response.setVerions(result.getVersions());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.aliases.list",
            path = "{region}/functions/FUNCTION_NAME/aliases"
    )
    public AliasesResponse functionsAliasesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("functionName") final String functionName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LambdaCaller.get(ListAliasesRequest.class, AliasesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListAliasesResult result = client.listAliases(
                    request
                            .withFunctionName(functionName)
                            .withMarker(page)
            );
            response.setAliases(result.getAliases());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.policy.get",
            path = "{region}/functions/FUNCTION_NAME/policy"
    )
    public PolicyResponse functionsPolicyGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("functionName") final String functionName
    ) throws AmazonUnparsedException {
        return LambdaCaller.get(GetPolicyRequest.class, PolicyResponse.class, credentials, region).execute((client, request, response) -> {
            final GetPolicyResult result = client.getPolicy(
                    request
                            .withFunctionName(functionName)
            );
            response.setPolicy(result.getPolicy());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "eventSourceMappings.list",
            path = "{region}/event-source-mappings"
    )
    public EventSourceMappingsResponse eventSourceMappingsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LambdaCaller.get(ListEventSourceMappingsRequest.class, EventSourceMappingsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListEventSourceMappingsResult result = client.listEventSourceMappings(
                    request
                            .withMarker(page)
            );
            response.setEventSourceMappings(result.getEventSourceMappings());
            response.setNextPage(result.getNextMarker());
        });
    }
}
