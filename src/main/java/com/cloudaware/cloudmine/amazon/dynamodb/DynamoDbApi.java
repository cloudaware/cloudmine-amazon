package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
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
}
