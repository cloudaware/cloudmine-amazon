package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTimeToLiveRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTimeToLiveResult;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ListTagsOfResourceRequest;
import com.amazonaws.services.dynamodbv2.model.ListTagsOfResourceResult;
import com.amazonaws.services.dynamodbv2.model.TagResourceRequest;
import com.amazonaws.services.dynamodbv2.model.UntagResourceRequest;
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

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "dynamodb",
        canonicalName = "DynamoDb",
        title = "Amazon DynamoDB",
        description = "Managed NoSQL Database",
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
public final class DynamoDbApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tables.list",
            path = "{region}/tables"
    )
    public TableNamesResponse tablesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DynamoDbCaller.get(ListTablesRequest.class, TableNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTablesResult result = client.listTables(
                    request.withExclusiveStartTableName(page)
            );
            response.setTableNames(result.getTableNames());
            response.setNextPage(result.getLastEvaluatedTableName());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tables.get",
            path = "{region}/tables/{tableName}"
    )
    public TableResponse tablesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("tableName") final String tableName
    ) throws AmazonUnparsedException {
        return DynamoDbCaller.get(DescribeTableRequest.class, TableResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTableResult result = client.describeTable(
                    request.withTableName(tableName)
            );
            response.setTable(result.getTable());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tables.getTtl",
            path = "{region}/tables/{tableName}/ttl"
    )
    public TableTtlResponse tablesGetTtl(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("tableName") final String tableName
    ) throws AmazonUnparsedException {
        return DynamoDbCaller.get(DescribeTimeToLiveRequest.class, TableTtlResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTimeToLiveResult result = client.describeTimeToLive(
                    request.withTableName(tableName)
            );
            response.setTtl(result.getTimeToLiveDescription());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "resources.tags.get",
            path = "{region}/resources/ARN/tags"
    )
    public TagsResponse resourcesTagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        return DynamoDbCaller.get(ListTagsOfResourceRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsOfResourceResult result = client.listTagsOfResource(request.withNextToken(page).withResourceArn(arn));
            response.setTags(result.getTags());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "resources.tags.tag",
            path = "{region}/resources/ARN/tags"
    )
    public AmazonResponse resourcesTagsTag(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn,
            final TagTagsRequest request
    ) throws AmazonUnparsedException {
        return DynamoDbCaller.get(TagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.tagResource(r.withResourceArn(arn).withTags(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "resources.tags.untag",
            path = "{region}/resources/ARN/tags"
    )
    public AmazonResponse resourcesTagsUntag(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn,
            @Named("tagKey") final List<String> tagKeys
    ) throws AmazonUnparsedException {
        return DynamoDbCaller.get(UntagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.untagResource(r.withResourceArn(arn).withTagKeys(tagKeys));
        });
    }
}
